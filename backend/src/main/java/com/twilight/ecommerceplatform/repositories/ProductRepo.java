package com.twilight.ecommerceplatform.repositories;

import com.twilight.ecommerceplatform.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Long> findPriceByIds(List<Long> ids);

}
