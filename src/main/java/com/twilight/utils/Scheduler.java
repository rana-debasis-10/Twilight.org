package com.twilight.utils;


import com.twilight.objects.Order;
import com.twilight.repositories.OrderRepository;
import com.twilight.services.DispatchService;
import com.twilight.types.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final OrderRepository repository;

    private final DispatchService dispatchService;

    @Scheduled(fixedDelay = 5000)
    public void retryOrders(){

//        List<Order> orders =
//
//                repository
//                        .findByStatusAndDriverSearchExpiresAtBefore(
//                                DeliveryStatus.searching_partner,
//                                Instant.now()
//
//                        );
//
//        for(Order order : orders){
//
//            dispatchService.dispatch("",order);
//
//        }

    }
}