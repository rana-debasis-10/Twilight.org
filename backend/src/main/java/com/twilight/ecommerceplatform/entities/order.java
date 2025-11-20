package com.twilight.ecommerceplatform.entities;

import com.twilight.ecommerceplatform.enums.PaymentMethods;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull
    private PaymentMethods paymentMethod;


}
