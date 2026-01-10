package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.entities.OrderItem;
import com.twilight.eCommercePlatform.repositories.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepo orderItemRepo;
    public List<OrderItem> getAllItemById(Long orderId){
        return orderItemRepo.findAllByOrderId(orderId);
    }
}
