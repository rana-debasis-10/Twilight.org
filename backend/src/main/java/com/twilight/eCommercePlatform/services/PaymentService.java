package com.twilight.eCommercePlatform.services;


import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.twilight.eCommercePlatform.dto.payment.PaymentResponseDTO;
import com.twilight.eCommercePlatform.dto.payment.WebhookResponseDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Value("${razorpay.key_secret}")
    private String keySecret;
    @Value("${razorpay.webhook.secret}")
    private String webhookSecret;

    /// code to verify payment response
    public HttpStatusCode verifyPayment(PaymentResponseDTO payment) {
        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", payment.getRazorpayOrderId());
        options.put("razorpay_payment_id", payment.getRazorpayPaymentId());
        options.put("razorpay_signature", payment.getRazorpaySignature());

        try{
            if(Utils.verifyPaymentSignature(options, keySecret)){
                return HttpStatusCode.valueOf(200);
            }
            else
                return HttpStatusCode.valueOf(401);
        }
        catch (RazorpayException e){
            return HttpStatusCode.valueOf(401);
        }
    }
    /// code to verify webhook
    public HttpStatusCode verifyWebhook(WebhookResponseDTO webhook){
        try{
            if(Utils.verifyWebhookSignature(webhook.getPayload(), webhook.getSignature(), webhookSecret)){
                return HttpStatusCode.valueOf(200);
            }
            else
                return HttpStatusCode.valueOf(401);
        }
        catch (RazorpayException e){
            return HttpStatusCode.valueOf(401);
        }
    }
}
