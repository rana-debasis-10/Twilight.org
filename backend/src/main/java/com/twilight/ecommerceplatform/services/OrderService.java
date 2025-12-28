package com.twilight.ecommerceplatform.services;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.twilight.ecommerceplatform.dto.OrderEntityResponseDTO;
import com.twilight.ecommerceplatform.dto.OrderRequestDTO;
import com.twilight.ecommerceplatform.componenets.SessionUser;
import com.twilight.ecommerceplatform.entities.*;
import com.twilight.ecommerceplatform.enums.DeliveryStatus;
import com.twilight.ecommerceplatform.enums.PaymentMethod;
import com.twilight.ecommerceplatform.enums.PaymentStatus;
import com.twilight.ecommerceplatform.repositories.OrderEntityRepo;
import com.twilight.ecommerceplatform.utility.Converter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.Instant;
import java.util.*;

public class OrderService {
    /// Razorpay public key
    @Value("${razorpay.key_id}")
    private String keyId;

    /// Razorpay private key
    @Value("${razorpay.key_secret}")
    private String keySecret;

    /// Taken to calculated price from backend for secure price calculation
    @Autowired
    private ProductService productService;

    /// Address service is called to update and make order address
    /// (order address are separate and copied separately to ensure that order address does not change )
    /// even if user changes their order
    @Autowired
    private AddressService addressService;


    /// user Service to save the order in their order list
    @Autowired
    private UserService userService;

    /// Order - Razorpay order and Order entity is for our database
    @Autowired
    private OrderEntityRepo orderEntityRepo;

    /// Creating an Order By User to Create Order///////////////////////////////////
    public Map<String, Object> createOrder(OrderRequestDTO orderDetails , @ModelAttribute SessionUser sessionUser) throws Exception {

        long amount = 0;

        List<Product> products = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : orderDetails.getProdIdAndQuantity().entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();

            Product product = productService.getProductById(productId);
            if (product == null) {
                throw new Exception("Invalid product ID: " + productId);
            }

            products.add(product);
            quantities.add(quantity);
        }

        /// Convert to order items
        List<OrderItem> orderItems = Converter.productsToOrderItems(products, quantities);

        // --- Calculate total amount ---
        for (OrderItem orderItem : orderItems) {
            amount += (long)orderItem.getSubtotal();
        }

        // --- Create receipt ---
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String shortReceipt = "twlt_" + uuid.substring(0, 20);

        // --- Razorpay order ID ---
        String razorpayOrderId = null;

        // --- Response map ---
        Map<String, Object> response = new HashMap<>();

        // --- Create Razorpay order if not COD ---
        if (orderDetails.getPaymentMethod() != PaymentMethod.CASH_ON_DELIVERY) {

            RazorpayClient client = new RazorpayClient(keyId, keySecret);

            JSONObject req = new JSONObject();
            req.put("amount", amount * 100); // smallest currency unit
            req.put("currency", orderDetails.getCurrency().toString());
            req.put("receipt", shortReceipt);

            Order order = client.orders.create(req);
            razorpayOrderId = order.get("id");

            response.put("orderId", razorpayOrderId);
            response.put("key", keyId);
            response.put("orderPlaced", "false");
        } else {
            response.put("orderPlaced", "true");
        }

        response.put("amount", amount);

        // --- Build OrderEntity ---
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setRazorpayOrderId(razorpayOrderId);
        orderEntity.setReceipt(shortReceipt);
        orderEntity.setAmount(amount);
        orderEntity.setCurrency(orderDetails.getCurrency());
        orderEntity.setItems(orderItems);
        orderEntity.setPaymentStatus(PaymentStatus.PENDING);
        orderEntity.setPaymentMethod(orderDetails.getPaymentMethod());
        orderEntity.setTimeStamp(Instant.now());

        // --- Set Address safely ---
        if (orderDetails.getUseUserAddress()) {
            Address address = addressService.getById(sessionUser.getPrimaryAddressId());
            Address copiedAddress = new Address();
            copiedAddress.setCity(address.getCity());
            copiedAddress.setState(address.getState());
            copiedAddress.setCountry(address.getCountry());
            copiedAddress.setStreet(address.getStreet());
            copiedAddress.setLandMark(address.getLandMark());
            copiedAddress.setPostalCode(address.getPostalCode());
            copiedAddress.setPrimaryAddress(false);
            orderEntity.setAddress(copiedAddress);

        } else if (orderDetails.getAddress() != null) {
            orderEntity.setAddress(Converter.addressDtoToAddress(orderDetails.getAddress()));
        } else {
            throw new Exception("Address is empty.");
        }

        // --- Set mobile number ---
        if (orderDetails.getUseUserMobile()) {
            orderEntity.setMobNo(sessionUser.getMobNo());
        } else if (orderDetails.getMobNo() != null) {
            orderEntity.setMobNo(orderDetails.getMobNo());
        } else {
            throw new Exception("Mobile number is empty.");
        }

        // --- Set User ---
        User user = userService.getById(sessionUser.getUserId());
        orderEntity.setUser(user);

        user.getOrders().add(orderEntity);
        userService.saveUser(user);

        return response;
    }

    /// View Orders in Batches of Ten For User to Create a Order/////////////////
    public List<OrderEntityResponseDTO> getOrderBatch(@ModelAttribute SessionUser sessionUser, int pageNum){
        Long userId=sessionUser.getUserId();
        return orderEntityRepo.findByUserId(userId,PageRequest.of(pageNum,10)).getContent();
    }

    /// For Admin View/////////////////////////////////////////////////////////
    public List<OrderEntity> getAllOrder(){
        return orderEntityRepo.findAll();
    }

    /// For Admin view ///////////////////////////////////////////////////////
    public boolean updateOrder(OrderEntity orderEntity){
        try{
            orderEntityRepo.save(orderEntity);
        }
        catch (Exception e){
            return false;
        }
       return true;
    }

    /// For Restraunt update //////////////////////////////////////////////
    public boolean updateStatusToPreparingFood(Long orderId){ OrderEntity order=orderEntityRepo.findById(orderId).get();
        if(order!=null){
            order.setDeliveryStatus(DeliveryStatus.FOOD_IS_BEING_PREPARED);
            return true;
        }
        else{
            return false;
        }}

    /// For Driver Update /////////////////////////////////////////////////
    public boolean updateStatusToDirverOnWay(Long orderId){ OrderEntity order=orderEntityRepo.findById(orderId).get();
        if(order!=null){
            order.setDeliveryStatus(DeliveryStatus.DRIVER_IS_ON_THE_WAY);
            return true;
        }
        else{
            return false;
        }}

    ///  For Driver Update ///////////////////////////////////////////////
    public boolean updateStatusToDelivered(Long orderId){ OrderEntity order=orderEntityRepo.findById(orderId).get();
        if(order!=null){
            order.setDeliveryStatus(DeliveryStatus.ORDER_CANCELLED);
            return true;
        }
        else{
            return false;
        }}

    /// Cancelled due to unavoidable circumstances //////////////////////
    public boolean updateStatusToCancelled(Long orderId){
        OrderEntity order=orderEntityRepo.findById(orderId).get();
        if(order!=null){
            order.setDeliveryStatus(DeliveryStatus.ORDER_CANCELLED);
            return true;
        }
        else{
            return false;
        }
    }

}
