package com.twilight.ecommerceplatform.dto;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
}

