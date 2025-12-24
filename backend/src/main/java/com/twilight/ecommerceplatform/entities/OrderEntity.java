package com.twilight.ecommerceplatform.entities;

import com.twilight.ecommerceplatform.annotations.ValidMobileNumber;
import com.twilight.ecommerceplatform.enums.Currency;
import com.twilight.ecommerceplatform.enums.DeliveryStatus;
import com.twilight.ecommerceplatform.enums.PaymentMethod;
import com.twilight.ecommerceplatform.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity {
    /// Database Order Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    /// Razorpay Order ID
    @Column(unique = true)
    private String razorpayOrderId;

    /// Amount of the order
    private long amount;

    /// Currency in which order is placed
    private Currency currency;

    /// Payment Status of the Payment
    private PaymentStatus paymentStatus;

    /// Payment Method
    private PaymentMethod paymentMethod;

    /// Delivery Status
    private DeliveryStatus deliveryStatus;

    /// Receipt of the order generated
    private String receipt;

    /// Time when the order is generated
    private Instant timeStamp;

    /// User of the Order owner of the order
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    /// Mobile Number of receiver
    @ValidMobileNumber
    private String mobNo;

    /// Address of the order
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;
    
    /// Order items that are ordered
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
}
