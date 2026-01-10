package com.twilight.eCommercePlatform.repositories;

import com.twilight.eCommercePlatform.entities.Address;
import com.twilight.eCommercePlatform.entities.User;
import com.twilight.eCommercePlatform.enums.UserRole;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
        @NotNull
        public Page<User> findAll(@NotNull Pageable pageable);
        public Optional<User> findByEmail(String email);
}
