package com.twilight.ecommerceplatform.services;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.twilight.ecommerceplatform.DataToObjects.OrderRequestDTO;
import com.twilight.ecommerceplatform.componenets.SessionUser;
import com.twilight.ecommerceplatform.entities.*;
import com.twilight.ecommerceplatform.enums.PaymentMethod;
import com.twilight.ecommerceplatform.enums.PaymentStatus;
import com.twilight.ecommerceplatform.utility.Converter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

public class OrderService {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    @Autowired
    private ProductService productService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;


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
        List<OrderItem> orderItems = Converter.orderItemToProduct(products, quantities);

        // --- Calculate total amount ---
        for (OrderItem orderItem : orderItems) {
            amount += orderItem.getSubtotal();
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

        // --- Set Address safely ---
        if (orderDetails.getUseUserAddress()) {
            Address address = addressService.getAddress(sessionUser.getPrimaryAddressId());
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
        User user = userService.getUser(sessionUser.getUserId());
        orderEntity.setUser(user);

        user.getOrders().add(orderEntity);
        userService.saveUser(user);

        return response;
    }
}
