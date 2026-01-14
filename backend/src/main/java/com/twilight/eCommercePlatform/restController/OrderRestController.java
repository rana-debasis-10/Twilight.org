package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.order.OrderRequest;
import com.twilight.eCommercePlatform.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/order")
@RestController
@CrossOrigin(origins = "*")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest order) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(order));
    }
}
