package com.twilight.repositories;

import com.twilight.objects.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLocationRepository extends JpaRepository<Location,Integer> {
}
