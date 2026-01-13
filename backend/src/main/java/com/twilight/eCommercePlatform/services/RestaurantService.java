package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.dto.entity.RestaurantDTO;
import com.twilight.eCommercePlatform.entities.Restaurant;
import com.twilight.eCommercePlatform.entities.RestaurantOwner;
import com.twilight.eCommercePlatform.entities.User;
import com.twilight.eCommercePlatform.enums.UserRole;
import com.twilight.eCommercePlatform.repositories.RestaurantRepo;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    UserService userService;

    public List<RestaurantDTO> getAllRestaurant(int pageNum){
        return restaurantRepo.findAll(PageRequest.of(pageNum,10))
                .getContent()
                .stream()
                .map(RestaurantDTO::new)
                .toList();
    }
    public List<Restaurant> getRestaurantByNameContainingIgnoreCase(String name, int pageNum){
        return restaurantRepo.findByNameContainingIgnoreCase(name,PageRequest.of(pageNum,15)).getContent();
    }
    public void createRestaurant(RestaurantDTO restaurantDTO, UserDetailsImpl userDetails){
        Restaurant restaurant = new Restaurant(restaurantDTO);
        User user = userService.getUserByEmail(userDetails.getEmail());
        user.setRole(UserRole.RESTAURANT_OWNER);
        RestaurantOwner owner = new RestaurantOwner();
        owner.setEmail(user.getEmail());
        restaurant.setOwner(owner);
        restaurant.setEmail(user.getEmail());
        owner.setRestaurant(restaurant);
        owner.setUser(user);
        owner.setUserId(user.getId());
        user.setRestaurantOwner(owner);
        userService.saveUser(user);
    }

    public Restaurant getByEmail(String email){
        return restaurantRepo.findByEmail(email).get();
    }
}
