package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.entities.Restaurant;
import com.twilight.eCommercePlatform.repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;
    public List<Restaurant> getAllRestaurant(int pageNum){
        return restaurantRepo.findAll(PageRequest.of(pageNum,10)).getContent();
    }
    public List<Restaurant> getRestaurantByNameContainingIgnoreCase(String name, int pageNum){
        return restaurantRepo.findByNameContainingIgnoreCase(name,PageRequest.of(pageNum,15)).getContent();
    }}
