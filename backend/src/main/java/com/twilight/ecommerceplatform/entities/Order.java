package com.twilight.ecommerceplatform.entities;

import com.twilight.ecommerceplatform.enums.PaymentMethods;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")   // IMPORTANT – avoids conflict with SQL keyword
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
