package com.twilight.eCommercePlatform.services;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.twilight.eCommercePlatform.dto.order.OrderEntityResponseDTO;
import com.twilight.eCommercePlatform.dto.order.OrderRequestDTO;
import com.twilight.eCommercePlatform.entities.*;
import com.twilight.eCommercePlatform.enums.DeliveryStatus;
import com.twilight.eCommercePlatform.enums.PaymentMethod;
import com.twilight.eCommercePlatform.enums.PaymentStatus;
import com.twilight.eCommercePlatform.mapper.AddressMapper;
import com.twilight.eCommercePlatform.repositories.OrderEntityRepo;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import com.twilight.eCommercePlatform.utility.Converter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
@Service
public class OrderService {

    /// Razorpay public key
    @Value("${razorpay.key.id}")
    private String keyId;

    /// Razorpay private key
    @Value("${razorpay.key.secret}")
    private String keySecret;


    @Autowired
    private UserService userService;

    @Autowired
    private OrderEntityRepo orderEntityRepo;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private Converter converter;

    /// Creating an order
    public Map<String, Object> createOrder(OrderRequestDTO orderDetails) throws Exception {


        List<OrderItem> orderItems = converter.ToOrderItems(orderDetails);

        long amount = 0;
        for (OrderItem orderItem : orderItems) {
            amount += (long)orderItem.getSubtotal();
        }

        /// Receipt Creation
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String shortReceipt = "twlt_" + uuid.substring(0, 20);

        /// Razorpay order ID
        String razorpayOrderId = null;

        /// Getting user from security context holder
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User user = userService.getUserByEmail(userDetailsImpl.getEmail());

        /// Response map
        Map<String, Object> response = new HashMap<>();


        /// Create Razorpay order if not COD

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
            response.put("amount", amount);
        } else {
            response.put("orderPlaced", "true");
        }

        /// Build OrderEntity

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setRazorpayOrderId(razorpayOrderId);
        orderEntity.setReceipt(shortReceipt);
        orderEntity.setAmount(amount);
        orderEntity.setCurrency(orderDetails.getCurrency());
        orderEntity.setItems(orderItems);
        orderEntity.setPaymentStatus(PaymentStatus.PENDING);
        orderEntity.setPaymentMethod(orderDetails.getPaymentMethod());
        orderEntity.setCreatedAt(Instant.now());

        /// Set Address

        Address address =  addressMapper.toAddress(orderDetails.getAddress());


        /// Set mobile number

        if (orderDetails.isDefaultMobNo()) {
            orderEntity.setMobNo(user.getMobNo());
        } else if (orderDetails.getMobNo() != null) {
            orderEntity.setMobNo(orderDetails.getMobNo());
        } else {
            throw new Exception("Mobile number is empty.");
        }

        user.getOrders().add(orderEntity);
        userService.saveUser(user);

        return response;
    }

    /// View Orders
    public List<OrderEntityResponseDTO> getOrders(int pageNum){
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String email= userDetailsImpl.getEmail();
        return orderEntityRepo.findByUserEmail(email,PageRequest.of(pageNum,10)).getContent();
    }

    /// For Admin View
    public List<OrderEntity> getAllOrder(int pageNum){
        return orderEntityRepo.findAll(PageRequest.of(pageNum, 10)).getContent();
    }

    /// For Admin view
    public boolean updateOrder(OrderEntity orderEntity){
        try{
            orderEntityRepo.save(orderEntity);
        }
        catch (Exception e){
            return false;
        }
       return true;
    }

    /// For Restaurant update
    public ResponseEntity<?> updateStatusToPreparingFood(Long orderId){
        Optional<OrderEntity> order = orderEntityRepo.findById(orderId);
        if(order.isPresent()){
        order.get().setDeliveryStatus(DeliveryStatus.FOOD_IS_BEING_PREPARED);
            return ResponseEntity.ok("Updated");
        }
        return ResponseEntity.notFound().build();
    }

    /// For Driver Update
    public ResponseEntity<?>  updateStatusToDriverOnWay(Long orderId){
        Optional<OrderEntity> order = orderEntityRepo.findById(orderId);
        if(order.isPresent()){
            order.get().setDeliveryStatus(DeliveryStatus.DRIVER_IS_ON_THE_WAY);
            return ResponseEntity.ok("Updated");
        }
        return ResponseEntity.notFound().build();
    }

    ///  For Driver Update
    public ResponseEntity<?>  updateStatusToDelivered(Long orderId){
        Optional<OrderEntity> order = orderEntityRepo.findById(orderId);
        if(order.isPresent()){
            order.get().setDeliveryStatus(DeliveryStatus.ORDER_DELIVERED);
            return ResponseEntity.ok("Updated");
        }
        return ResponseEntity.notFound().build();
    }

    /// Cancelled due to unavoidable circumstances
    public boolean updateStatusToCancelled(Long orderId){
        Optional<OrderEntity> order = orderEntityRepo.findById(orderId);
        order.ifPresent(orderEntity -> orderEntity.setDeliveryStatus(DeliveryStatus.ORDER_CANCELLED));
        return true;
    }

}
