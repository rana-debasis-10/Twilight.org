package com.twilight.dataTransferObjects;


public record Payment(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature){}

