package com.twilight.dataTransferObjects;


import jakarta.validation.constraints.NotBlank;

public record Payment(@NotBlank String razorpayOrderId,@NotBlank String razorpayPaymentId,@NotBlank String razorpaySignature){}

