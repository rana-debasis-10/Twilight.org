package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.dto.OrderRequestDTO;
import com.twilight.ecommerceplatform.componenets.SessionUser;
import com.twilight.ecommerceplatform.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO order,  @ModelAttribute SessionUser sessionUser) {

        try {
            return ResponseEntity.ok(orderService.createOrder(order,sessionUser));

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating order");
        }
    }
}
