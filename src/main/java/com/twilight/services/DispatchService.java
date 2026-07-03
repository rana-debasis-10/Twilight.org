package com.twilight.services;

import com.twilight.dataTransferObjects.MenuUpdateR;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

public interface DispatchService {
    void dispatch(String topic, Object event);

    @KafkaListener(
            topics = "Menu-Update",
            groupId = "product-consumer"
    )
    void menuUpdater(MenuUpdateR request);

    @KafkaListener(
            topics = "orders",
            groupId = "Delivery-Partner-Assignment"
    )
    void partnerAssigner(String razorpayOrderId) throws IOException;

    @KafkaListener(
            topics = "notify-outlet-cod",
            groupId = "outlet-notifier-cod"
    )
    void outletNotifier(Integer orderId);
}
