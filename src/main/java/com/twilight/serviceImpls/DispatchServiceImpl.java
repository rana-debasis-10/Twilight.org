package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.MenuUpdateR;
import com.twilight.dataTransferObjects.OrderSummary;
import com.twilight.dataTransferObjects.Location;
import com.twilight.dataTransferObjects.WebSocketMessage;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.managers.SessionManager;
import com.twilight.objects.*;
import com.twilight.repositories.OrderRepository;
import com.twilight.services.DispatchService;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.repositories.ProductRepository;
import com.twilight.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DispatchServiceImpl implements DispatchService {
    @Autowired
    KafkaTemplate<String,Object> kafka;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutletRepository outletRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SessionManager sessionManager;

    @Autowired
    LocationService locationService;
    @Override
    public void dispatch(String topic, Object event) throws SomethingWentWrongException {
        try {
            kafka.send(topic,event);
        } catch (Exception e) {
            throw new SomethingWentWrongException(e.getMessage(),"Unable to complete the request");
        }
    }

    @KafkaListener(
            topics = "update-menu",
            groupId = "menu-updater"
    )
    @Override
    public void menuUpdater(MenuUpdateR request){
        try {
            Product product  = productRepository
                    .findById(request.productId())
                    .orElse(null);
            List<Outlet> outlets = outletRepository
                    .findAllByRestaurantId(
                            request.restaurantId()
                    );

            if(product==null || outlets.isEmpty())
                throw new RuntimeException("Product not found");
            List<Food> foods= new ArrayList<>();
            outlets.forEach(outlet->{
                Food food = new Food();
                food.setProduct(product);
                food.setOutlet(outlet);
                foods.add(food);
            });
            foodRepository.saveAll(foods);
        } catch (Exception e) {
            log.error("\n{}\n",e.getMessage());
        }

    }

    @KafkaListener(
            topics = "assign-partner",
            groupId = "partner-assigner"
    )
    @Override
    public void partnerAssigner(String razorpayOrderId) throws IOException {
        Order order= orderRepository.findByRazorpayOrderId(razorpayOrderId);
        if(order==null){
            log.warn("Order not found");
            return;
        }
        Optional<Outlet> optionalObject = outletRepository.findById(order.getOutletId());
        if(optionalObject.isEmpty()){
            return;
        }
        Outlet outlet = optionalObject.get();
        List<String> drivers = locationService
                .findNearByDriver(
                        new Location(outlet.getLatitude(),outlet.getLongitude())
                );
        OrderSummary orderSummary  =
                new OrderSummary(
                        order.getId(),
                        order.getDeliveryMobNo(),
                        order.getDeliveryAddress(),
                        outlet.getLatitude(),
                        outlet.getLongitude()
                );

        for(String driver: drivers){
            try {
                sessionManager.send(driver,orderSummary);
            } catch (IOException e) {
                log.warn("Exception Occurred during delivery partner assignment");
            }
        }
    }

    @KafkaListener(
            topics = "notify-outlet-cod",
            groupId = "outlet-notifier-cod"
    )
    @Override
    public void outletNotifier(Integer orderId){
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null){
            log.warn("Order not found to notify outlet");
            return;
        }
        try {
            sessionManager.send(
                    order.getOutletId().toString(),
                    new WebSocketMessage(
                            "new-order",
                            order.getItems()
                    )
            );
        } catch (IOException e) {
            log.warn(
                    "Failed to notify Outlet for cash on delivery due to {}"
                    ,e.getLocalizedMessage()
            );
        }

    }
    @KafkaListener(
            topics = "notify-outlet",
            groupId = "outlet-notifier"
    )
    @Override
    public void outletNotifier(String razorpayOrderId){
        Order order = orderRepository.findByRazorpayOrderId(razorpayOrderId);
        if(order == null){
            log.warn("Order not found to notify outlet in online payment");
            return;
        }
        try {
            sessionManager.send(
                    order.getOutletId().toString(),
                    new WebSocketMessage(
                            "new-order",
                            order.getItems()
                    )
            );
        } catch (IOException e) {
            log.warn(
                    "Failed to notify Outlet for online payment due to {}",
                    e.getLocalizedMessage()
            );
        }

    }

}
