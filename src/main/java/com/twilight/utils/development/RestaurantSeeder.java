package com.twilight.utils.development;
import com.twilight.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class RestaurantSeeder {
    public final RestaurantRepository restaurantRepository;
    public void seed(){};

}
