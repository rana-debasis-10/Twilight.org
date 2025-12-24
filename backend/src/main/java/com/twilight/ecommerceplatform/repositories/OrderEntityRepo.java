package com.twilight.ecommerceplatform.repositories;

import com.twilight.ecommerceplatform.dto.OrderEntityResponseDTO;
import com.twilight.ecommerceplatform.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderEntityRepo extends JpaRepository<OrderEntity,Integer> {
    Page<OrderEntityResponseDTO> findByUserId(Long userId, Pageable pageable);
}
