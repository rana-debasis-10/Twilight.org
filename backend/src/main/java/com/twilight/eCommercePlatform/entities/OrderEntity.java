package com.twilight.eCommercePlatform.entities;

import com.twilight.eCommercePlatform.annotations.ValidEmail;
import com.twilight.eCommercePlatform.annotations.ValidMobileNumber;
import com.twilight.eCommercePlatform.enums.Currency;
import com.twilight.eCommercePlatform.enums.DeliveryStatus;
import com.twilight.eCommercePlatform.enums.PaymentMethod;
import com.twilight.eCommercePlatform.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String razorpayOrderId;
    @ValidEmail
    @Column(unique = true,nullable = false)
    private String userEmail;
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
