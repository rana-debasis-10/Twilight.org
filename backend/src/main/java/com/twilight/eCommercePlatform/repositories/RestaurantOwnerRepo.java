package com.twilight.eCommercePlatform.repositories;


import com.twilight.eCommercePlatform.entities.Restaurant;
import jakarta.persistence.OneToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOwnerRepo extends JpaRepository<Restaurant,Long> {

}
