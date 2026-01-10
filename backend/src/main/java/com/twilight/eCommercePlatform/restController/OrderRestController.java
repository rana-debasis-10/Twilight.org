package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.order.OrderRequestDTO;
import com.twilight.eCommercePlatform.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
@CrossOrigin(origins = "*")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO order) {

        try {
            return ResponseEntity.ok(orderService.createOrder(order));

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating order");
        }
    }
}
