package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.payment.WebhookResponseDTO;
import com.twilight.eCommercePlatform.services.PaymentService;
import com.twilight.eCommercePlatform.dto.payment.PaymentResponseDTO;
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
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentResponseDTO payment) {

        return new ResponseEntity<>(paymentService.verifyPayment(payment));
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> verifyWebhook(@RequestBody WebhookResponseDTO webhook)  {
        return new ResponseEntity<>(paymentService.verifyWebhook(webhook));
    }
}
