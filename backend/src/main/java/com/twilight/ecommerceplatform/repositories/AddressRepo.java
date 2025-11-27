package com.twilight.ecommerceplatform.repositories;

import com.twilight.ecommerceplatform.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
