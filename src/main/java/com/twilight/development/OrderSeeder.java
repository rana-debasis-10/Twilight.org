package com.twilight.development;

import com.twilight.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSeeder {
    public final OrderRepository orderRepository;
    public void seed(){};

}
