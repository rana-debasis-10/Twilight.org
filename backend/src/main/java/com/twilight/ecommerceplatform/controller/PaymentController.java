package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.services.PaymentService;
import com.twilight.ecommerceplatform.DataToObjects.PaymentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Value("${razorpay.key_secret}")
    private String keySecret;
    @Autowired
    private PaymentService paymentService;

    /** VERIFY PAYMENT SIGNATURE **/
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentResponseDTO data) {

        try {
            String orderId = data.getRazorpayOrderId();
            String paymentId = data.getRazorpayPaymentId();
            String signature = data.getRazorpaySignature();

            String payload = STR."\{orderId}|\{paymentId}";

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(keySecret.getBytes(), "HmacSHA256"));

            String generatedSignature =
                    new String(org.springframework.security.crypto.codec.Hex.encode(mac.doFinal(payload.getBytes())));

            if (generatedSignature.equals(signature)) {
                return ResponseEntity.ok("Payment Verified");
            } else {
                return ResponseEntity.status(400).body("Invalid Signature");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error verifying payment");
        }
    }


    /** WEBHOOK ENDPOINT **/
    @PostMapping("/webhook")
    public ResponseEntity<String> webhook(@RequestBody String payload) {
        System.out.println("WEBHOOK RECEIVED:");
        System.out.println(payload);

        return ResponseEntity.ok("Webhook received");
    }
}
