package com.twilight.eCommercePlatform.repositories;

import com.twilight.eCommercePlatform.entities.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @NotNull
    public Page<Product> findAll(@NotNull Pageable pageable);
    public Page<Product> findByNameContainingIgnoreCase(String name ,Pageable pageable);
}
