package com.twilight.endPoints;

import com.twilight.dataTransferObjects.CustomerR;
import com.twilight.dataTransferObjects.OrderR;
import com.twilight.dataTransferObjects.Payment;
import com.twilight.utils.mappers.CustomerMapper;

import com.twilight.utils.mappers.OrderMapper;
import com.twilight.services.CustomerService;
import com.twilight.services.OrderService;
import com.twilight.services.PaymentService;
import com.twilight.security.UserContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/customer")
@Validated
@AllArgsConstructor
public class CustomerEndpoints {

    private CustomerService customerService;

    private OrderService orderService;

    private PaymentService paymentService;

    private CustomerMapper customerMapper;

    private OrderMapper orderMapper;

    private UserContext user;

    @GetMapping("/load")
    @Transactional
    CustomerR loadCustomer() {
        String mobNo = user.getMobNo();
        return customerMapper.toCustomerR(customerService.load(mobNo));
    }

    @PostMapping("/order/create")
    @Transactional
    Map<String, Object> createOrder(@RequestBody @Valid OrderR orderDetails) throws Exception {
        return orderService.create(user.getMobNo(),orderMapper.toOrder(orderDetails));
    }

    @PostMapping("/payment/verify")
    @Transactional
    public void verifyPayment(@RequestBody @Valid Payment payment){
        paymentService.verifyPayment(payment);
    }

}
