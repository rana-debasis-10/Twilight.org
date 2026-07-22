package com.twilight.repositories;

import com.twilight.objects.OrderLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLocationRepository extends JpaRepository<OrderLocation,Integer> {
}
