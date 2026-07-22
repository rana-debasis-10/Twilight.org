package com.twilight.services;

import com.twilight.dataTransferObjects.OrderDeliveryRequest;
import com.twilight.dataTransferObjects.OutletOrderRequest;
import com.twilight.dataTransferObjects.WebSocketMessage;
import com.twilight.exceptions.NotFoundException;
import com.twilight.managers.WebsocketSessionManager;
import com.twilight.objects.*;
import com.twilight.repositories.*;
import com.twilight.types.InvitationStatus;
import com.twilight.utils.Constants;
import com.twilight.utils.mappers.LocationMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor

public class EventService {
    final ProductRepository productRepository;

    final OutletInvitationRepository invitationRepository;

    final OrderRepository orderRepository;

    final WebsocketSessionManager websocketSessionManager;

    final OutletRepository outletRepository;

    final LocationService locationService;

    final LocationMapper locationMapper;


    @KafkaListener(
            topics = Constants.UPDATE_MENU_TOPIC,
            groupId = Constants.UPDATE_MENU_LISTENER
    )
    public void update_menu(Integer productId){
        Product product = productRepository.findById(productId).orElseThrow(NotFoundException::new);
        List<Outlet> outlets = product.getRestaurant().getOutlet();
        List<Food> foods = new ArrayList<>();
        product.setFood(foods);
        for (Outlet outlet : outlets) {
            Food food = new Food();
            food.setAvailable(false);
            food.setPrice(product.getPrice());
            food.setOutlet(outlet);
            food.setProduct(product);
        }
        productRepository.save(product);
    }

    @KafkaListener(
            topics = Constants.INVITATION_EXPIRATION_TOPIC,
            groupId = Constants.INVITATION_EXPIRATION_LISTENER
    )
    public void update_invitation_expired(String inviteeMobNo){
        List<OutletInvitation> invitations = invitationRepository.
                findAllByInviteeMobNo(inviteeMobNo);
        invitations = invitations.stream().filter(
                outletInvitation -> {
                    return outletInvitation.getStatus().equals(InvitationStatus.pending);
                }
        ).toList();
        invitationRepository.saveAll(invitations);
    }

    @KafkaListener(
            topics = Constants.NEW_ORDER_COD_TOPIC,
            groupId = Constants.NEW_ORDER_COD_LISTENER
    )
    public void notify_outlet_cod(OutletOrderRequest orderRequest){
        try {
            websocketSessionManager.send(orderRequest.getMobNo(),new WebSocketMessage(Constants.WEB_SOCKET_NEW_ORDER_MESSAGE,orderRequest));
        } catch (IOException e) {
            log.warn("{}",e.getMessage());
        }

    }

    @KafkaListener(
            topics = Constants.ASSIGN_DELIVERY_PARTNER_TOPIC,
            groupId = Constants.ASSIGN_DELIVERY_PARTNER_LISTENER
    )
    public void notify_delivery_partner(String orderId){
        Order order = orderRepository.findById(Integer.parseInt(orderId)).orElse(null);
        if(order == null){
            log.warn("Order not found for Id : {}",orderId);
            return ;
        }
        Location deliveryLocation = order.getDeliveryLocation();
        Location outletLocation = order.getOutlet().getLocation();
        if(outletLocation == null || deliveryLocation == null){
            log.warn("Location not found for Id : {}",orderId);
        }
        List<String> driverIds = locationService.findNearByDriver(outletLocation);
        WebSocketMessage message = new WebSocketMessage(
                Constants.WEB_SOCKET_NEW_ORDER_MESSAGE,
                new OrderDeliveryRequest(
                        locationMapper.toLocation(deliveryLocation),
                        locationMapper.toLocation(outletLocation),
                        order.getDeliveryCharge())
        );
        driverIds.forEach(driverId -> {
            try {
                websocketSessionManager.send(driverId,message);
            } catch (IOException e) {
                log.warn("error occurred here for websocket{}",e.getMessage());

            }
        });

    }


}
