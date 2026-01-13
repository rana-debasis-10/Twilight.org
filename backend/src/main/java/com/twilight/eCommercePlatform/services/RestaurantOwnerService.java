package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.entities.RestaurantOwner;
import com.twilight.eCommercePlatform.repositories.RestaurantOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantOwnerService {
    @Autowired
    RestaurantOwnerRepo restaurantOwnerRepo;
    RestaurantOwner getByEmail(String email){
        return restaurantOwnerRepo.findByEmail(email).orElse(null);
    }
}
