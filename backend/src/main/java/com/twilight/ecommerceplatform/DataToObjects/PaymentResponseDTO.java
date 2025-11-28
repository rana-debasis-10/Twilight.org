package com.twilight.ecommerceplatform.DataToObjects;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
}

