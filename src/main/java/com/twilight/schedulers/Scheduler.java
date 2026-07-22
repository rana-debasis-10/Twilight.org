package com.twilight.schedulers;


import com.twilight.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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