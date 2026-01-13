package com.twilight.eCommercePlatform.repositories;


import com.twilight.eCommercePlatform.entities.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantOwnerRepo extends JpaRepository<RestaurantOwner,Long> {
    Optional<RestaurantOwner> findByEmail(String Email);
}
