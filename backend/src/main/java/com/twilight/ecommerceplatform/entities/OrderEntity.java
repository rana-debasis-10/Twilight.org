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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Column(unique = true)
    private String razorpayOrderId;
    private long amount;
    private Currency currency;
    private PaymentStatus paymentStatus=PaymentStatus.UNVERIFIED;
    private PaymentMethod paymentMethod;
    private String receipt;
    private DeliveryStatus deliveryStatus;
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ValidMobileNumber
    private String mobNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;



    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
}
