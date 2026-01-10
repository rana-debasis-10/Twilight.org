package com.twilight.eCommercePlatform.repositories;

import com.twilight.eCommercePlatform.entities.Address;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
    @NotNull Page<Address> findAll(@NotNull Pageable pageable);
}
