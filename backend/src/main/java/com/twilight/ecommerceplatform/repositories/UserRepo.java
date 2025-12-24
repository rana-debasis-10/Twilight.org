package com.twilight.ecommerceplatform.repositories;

import com.twilight.ecommerceplatform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long>{
    public User findByEmail(String email);
}
