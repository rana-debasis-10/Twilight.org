package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.OutletR;
import com.twilight.dataTransferObjects.WebSocketMessage;
import com.twilight.exceptions.BadRequestException;
import com.twilight.exceptions.FailedToNotifyException;
import com.twilight.exceptions.NotFoundException;
import com.twilight.managers.WebsocketSessionManager;
import com.twilight.objects.*;
import com.twilight.repositories.*;
import com.twilight.services.ManagerService;
import com.twilight.types.InvitationStatus;
import com.twilight.types.OrderStatus;
import com.twilight.types.OutletStatus;
import com.twilight.types.Role;
import com.twilight.utils.Constants;
import com.twilight.utils.annotations.MobileNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ManagerServiceImpl implements ManagerService {

    final OutletInvitationRepository invitationRepository;

    final ManagerRepository managerRepository;

    final OutletRepository outletRepository;

    final FoodRepository foodRepository;

    final UserRepository userRepository;

    final OrderRepository orderRepository;

    final KafkaTemplate<String, String> kafka;

    final WebsocketSessionManager websocketSessionManager;


    @Override
    public List<OutletInvitation> viewAllInvitation(@MobileNumber String mobNo){
        return invitationRepository.findAllByInviteeMobNo(mobNo);
    };

    @Override
    public void acceptInvitation(String inviteeMobNo, Integer invitationId) throws NotFoundException{;
        OutletInvitation invitation = invitationRepository.findByIdAndInviteeMobNo(invitationId,inviteeMobNo).orElseThrow(NotFoundException::new);
        if(invitation.getStatus().equals(InvitationStatus.expired))
            throw new NotFoundException();
        User user = userRepository.findById(inviteeMobNo)
                .orElse(
                        User
                                .builder()
                                .mobNo(inviteeMobNo)
                                .blocked(false)
                                .build());
        Manager manager = new Manager();
        user.getRoles().add(Role.manager);
        manager.setUser(user);
        Outlet outlet = invitation.getOutlet();
        Manager oldManager = outlet.getManager();
        outlet.setManager(manager);
        if(oldManager != null){
            oldManager.setOutlet(null);
            User oldUser =userRepository.findById(oldManager.getMobNo()).orElseThrow(NotFoundException::new);
            oldUser.setManager(null);
            oldUser.getRoles().remove(Role.manager);
            userRepository.save(oldUser);
            oldManager.setUser(null);
            managerRepository.delete(oldManager);
        }
        manager.setOutlet(outlet);
        user.setManager(manager);
        userRepository.save(user);
        invitation.setStatus(InvitationStatus.accepted);
        invitationRepository.save(invitation);
        kafka.send(Constants.INVITATION_EXPIRATION_TOPIC,inviteeMobNo);
    }

    @Override
    public void updateFoodPrice(Integer outletId, Integer foodId, Double price) throws NotFoundException {
        Food food = foodRepository.
                findByIdAndOutletId(foodId,outletId).
                    orElseThrow(
                            NotFoundException::new);

        food.setPrice(price);
        foodRepository.save(food);
    }

    @Override
    public void makeFoodAvailable(Integer outletId, Integer foodId) throws NotFoundException {
        updateFoodAvailability(outletId,foodId,true);
    }

    private void updateFoodAvailability(Integer outletId, Integer foodId, boolean available) throws NotFoundException {
        Food food = foodRepository.
                findByIdAndOutletId(foodId,outletId).
                orElseThrow(NotFoundException::new);
        food.setAvailable(available);
    }

    @Override
    public void makeFoodUnavailable(Integer outletId, Integer foodId) throws NotFoundException {
        updateFoodAvailability(outletId,foodId,false);
    }

    @Override
    public void openOutlet(Integer outletId) throws NotFoundException {
        operateOutlet(outletId, OutletStatus.open);
    }

    @Override
    public void closeOutlet(Integer outletId) throws NotFoundException {
        operateOutlet(outletId,OutletStatus.closed);
    }

    @Override
    public OutletR viewOutlet(Integer outletId) throws BadRequestException {
        return outletRepository.findByOutletId(outletId).orElseThrow(NotFoundException::new);

    }

    @Override
    public List<Food> getAllFoods(Integer outletId) throws NotFoundException {
        return foodRepository.findByOutletId(outletId);
    }
    private void operateOutlet(Integer outletId,OutletStatus status)throws NotFoundException {
        Outlet outlet = outletRepository
                .findById(outletId)
                .orElseThrow(NotFoundException::new);
        outlet.setStatus(status);
        outletRepository.save(outlet);
    }

    @Override
    public void acceptOrder(String mobNo, Integer orderId, boolean accept) throws IOException {
        Order order = orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
        if(order.getOutlet().getManager().getMobNo().equals(mobNo)){
            if(accept){
                order.setStatus(OrderStatus.preparing);
                orderRepository.save(order);


            }
            else{
                order.setStatus(OrderStatus.outlet_rejected);
            }
            try {
                WebSocketMessage message = new WebSocketMessage(accept?Constants.ORDER_STARTED_PREPARING:Constants.ORDER_REJECTED,null);
                websocketSessionManager.send(order.getCustomer().getMobNo(),message );
            } catch (IOException e) {
                throw new FailedToNotifyException();
            }
            kafka.send(Constants.ASSIGN_DELIVERY_PARTNER_TOPIC,order.getId().toString());
        }
    }
}

