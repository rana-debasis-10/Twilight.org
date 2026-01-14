package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.entity.RestaurantDTO;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import com.twilight.eCommercePlatform.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantRestController{
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/find/{restName}/{pageNum}")
    List<RestaurantDTO> findRestaurant(@PathVariable String restName, @PathVariable int pageNum){
        return restaurantService.getRestaurantByNameContainingIgnoreCase(restName,pageNum)
                .stream()
                .map(RestaurantDTO::new)
                .toList();
    }
}
