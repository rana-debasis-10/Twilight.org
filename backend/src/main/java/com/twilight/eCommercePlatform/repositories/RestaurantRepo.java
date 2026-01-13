package com.twilight.eCommercePlatform.repositories;

import com.twilight.eCommercePlatform.entities.Restaurant;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
    @NotNull
    public Page<Restaurant> findAll(@NotNull Pageable pageable);
    public Page<Restaurant> findByNameContainingIgnoreCase(String name, Pageable pageable);
    public Optional<Restaurant> findByEmail(String email);
}
