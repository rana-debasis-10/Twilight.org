package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.payment.WebhookResponse;
import com.twilight.eCommercePlatform.services.PaymentService;
import com.twilight.eCommercePlatform.dto.payment.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentResponse payment) {

        return new ResponseEntity<>(paymentService.verifyPayment(payment));
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> verifyWebhook(@RequestBody WebhookResponse webhook)  {
        return new ResponseEntity<>(paymentService.verifyWebhook(webhook));
    }
}
