package com.twilight.eCommercePlatform.repositories;

import com.twilight.eCommercePlatform.dto.order.OrderEntityResponse;
import com.twilight.eCommercePlatform.entities.OrderEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderEntityRepo extends JpaRepository<OrderEntity,Long> {
    Page<OrderEntityResponse> findByUserEmail(String email, Pageable pageable);
    @NotNull Page<OrderEntity> findAll(@NotNull Pageable pageable);
}
