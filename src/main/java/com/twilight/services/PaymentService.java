package com.twilight.services;


import com.razorpay.RazorpayException;
import com.twilight.dataTransferObjects.Payment;
import com.twilight.exceptions.UnAuthorizedException;

import java.util.Map;

public interface PaymentService {
    Map<String, Object> createPayment(Double total, String currency, String receipt) throws RazorpayException;
    void verifyPayment(Payment payment) throws UnAuthorizedException;

}
