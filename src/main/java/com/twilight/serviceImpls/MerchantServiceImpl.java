package com.twilight.serviceImpls;

import com.twilight.types.InvitationStatus;
import com.twilight.types.Role;
import com.twilight.utils.Constants;
import com.twilight.utils.annotations.MobileNumber;
import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.dataTransferObjects.Location;
import com.twilight.exceptions.NotFoundException;
import com.twilight.objects.*;
import com.twilight.repositories.*;
import com.twilight.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    final RestaurantRepository restaurantRepository;

    final MerchantRepository merchantRepository;

    final OutletInvitationRepository invitationRepository;

    final OutletRepository outletRepository;

    final ProductRepository productRepository;

    final KafkaTemplate<String,Object> kafka;

    final UserRepository userRepository;



    @Override
    public void create(Merchant merchant, Restaurant restaurant) {

        User user = userRepository.findById(merchant.getMobNo())
                .orElse(
                        User
                                .builder()
                                .mobNo(
                                        merchant.getMobNo()
                                ).
                                blocked(false)
                                .build());
        user.getRoles().add(Role.merchant);
        user.setMerchant(merchant);
        merchant.setUser(user);
        restaurant.setMerchant(merchant);
        merchant.setRestaurant(restaurant);
        userRepository.save(user);
    }


    @Override
    public void createOutlet(String mobNo, Location location) {
        Merchant merchant = merchantRepository
                .findById(mobNo)
                .orElseThrow(NotFoundException::new);
        Outlet outlet = new Outlet();
        outlet.setMerchant(merchant);
        outlet.setRestaurant(merchant.getRestaurant());

    }

    @Override
    public OutletInvitation invite(String mobNo,Integer outletId, String inviteeMobNo) {
        Outlet outlet = outletRepository.findByIdAndMerchantMobNo(outletId, mobNo).orElseThrow(NotFoundException::new);
        OutletInvitation oldInvitation = outlet.getOutletInvitation();
        if(oldInvitation != null){
            oldInvitation.setStatus(InvitationStatus.pending);
            oldInvitation.setInviteeMobNo(inviteeMobNo);
            outletRepository.save(outlet);
            return oldInvitation;
        }
        OutletInvitation invitation = OutletInvitation.builder()
                .outlet(outlet)
                .inviteeMobNo(inviteeMobNo)
                .merchant(outlet.getMerchant())
                .build();
        return invitationRepository.save(invitation);
    }

    @Override
    public List<OutletDetailed> viewAllOutlets(@MobileNumber String merchantMobNo){
        return outletRepository.findAllByMerchantMobNo(merchantMobNo);
    }

    @Override
    public List<OutletInvitation> viewAllInvitation(@MobileNumber String merchantMobNo){
        return invitationRepository.findAllByMerchantMobNo(merchantMobNo);
    }


    @Override
    public void updateMenu(String mobNo, List<Product> products) throws NotFoundException {
        Restaurant restaurant = restaurantRepository.findByMerchantMobNo(mobNo).orElseThrow(NotFoundException::new);
        products.forEach(product -> product.setRestaurant(restaurant));
        products = productRepository.saveAll(products);
        products.forEach(product -> kafka.send(Constants.UPDATE_MENU_TOPIC, product.getId()));
    }


}
