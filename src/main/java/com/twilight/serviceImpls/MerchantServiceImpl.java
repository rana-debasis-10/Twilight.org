package com.twilight.serviceImpls;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.MenuUpdateR;
import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.dataTransferObjects.Location;
import com.twilight.exceptions.BadRequestException;
import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.*;
import com.twilight.repositories.*;
import com.twilight.services.DispatchService;
import com.twilight.services.MerchantService;
import com.twilight.types.InvitationStatus;
import com.twilight.types.OutletStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    OutletInvitationRepository invitationRepository;

    @Autowired
    OutletRepository outletRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DispatchService dispatchService;



    @Override
    public void createMerchant(Merchant merchant, Restaurant restaurant) {
        merchant.setRestaurant(restaurant);
        restaurant.setMerchant(merchant);
        merchantRepository.save(merchant);
    }
    @Override
    public void getMerchant(String mobNo) {
        merchantRepository.findById(mobNo).orElseThrow(
                ()-> new NotFoundException(
                        "Merchant not found"
                        ,"Could not find you linked account"));
    }

    @Override
    public void createOutlet(String mobNo, Location location) {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        Outlet outlet = new Outlet();
        outlet.setLatitude(location.latitude());
        outlet.setLongitude(location.longitude());
        outlet.setOutletStatus(OutletStatus.closed);
        outlet.setMerchantMobNo(mobNo);
        outlet.setRestaurant(restaurant);

        outletRepository.save(outlet);
    }

    @Override
    public OutletInvitation invite(String inviterMobNo, String inviteeMobNo, Integer outletId) {
        Outlet outlet = outletRepository.findById(outletId)
                            .orElseThrow(
                                    ()-> new NotFoundException(
                                            "User trying to send invitation for outlet that does not exist",
                                            "Outlet does not exist"
                                    ));
        if(!outlet.getMerchantMobNo().equals(inviterMobNo))
            throw new UnAuthorizedException(
                    "User is trying to send invitation of outlet that is not his",
                    "Outlet does not belong to your restaurant"
            );
        OutletInvitation invitation = new OutletInvitation();
        invitation.setInviterMobileNo(inviterMobNo);
        invitation.setInviteeMobileNo(inviteeMobNo);
        invitation.setStatus(InvitationStatus.pending);
        invitation.setOutletId(outletId);
        return invitationRepository.save(invitation);
    }

    @Override
    public OutletInvitation inviteSomeoneElse(String merchantMobNo, String inviteeMobNo, Integer outletId) throws BadRequestException {
        OutletInvitation invitation = invitationRepository.findByOutletId(outletId)
                .orElseThrow(
                    ()-> new BadRequestException(
                            "User is trying to find a invitation",
                            "Invitation does not exist"));
        if(!invitation.getInviterMobileNo().equals(merchantMobNo)){
            throw new UnAuthorizedException("User is trying unauthorized access","Unauthorized");
        }

        invitation.setInviteeMobileNo(inviteeMobNo);
        return invitationRepository.save(invitation);
    }
    @Override
    public List<OutletDetailed> viewAllOutlets(@MobileNumber String merchantMobNo){
        return outletRepository.findAllByMerchantMobNo(merchantMobNo);
    };

    @Override
    public List<OutletInvitation> viewAllInvitation(@MobileNumber String merchantMobNo){
        return invitationRepository.findAllByInviterMobileNo(merchantMobNo);
    };

    @Override
    public Restaurant findRestaurantByMobNo(String mobNo) throws NotFoundException {
        return restaurantRepository
                .findByMerchantMobNo(mobNo)
                .orElseThrow(
                        ()->new NotFoundException(
                                "User is trying to find restaurant",
                                "Not restaurant linked to your mobile number"
                        )
                );
    }

    @Override
    public void addAllToMenu(String mobNo, List<Product> products) throws NotFoundException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        restaurant.setMenuAdded(true);
        products.forEach(product -> {
            product.setRestaurant(restaurant);
        });
        restaurant.setProducts(products);
        restaurantRepository.save(restaurant);
    }
    @Override
    public void addToMenu(String mobNo, Product product) throws NotFoundException , SomethingWentWrongException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        product.setRestaurant(restaurant);
        product = productRepository.save(product);
        MenuUpdateR request = new MenuUpdateR(product.getId(),restaurant.getId());
        dispatchService.dispatch("update-menu",request);
    }

    @Override
    public void checkForMenuAdded(String mobNo) throws UnAuthorizedException {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        if(restaurant.isMenuAdded())
            throw new UnAuthorizedException("User is trying to add menu multiple times","Menu can only be added once");
    }


}
