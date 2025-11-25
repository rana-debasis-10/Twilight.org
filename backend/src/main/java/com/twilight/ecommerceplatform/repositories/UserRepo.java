package com.twilight.ecommerceplatform.repositories;

import com.twilight.ecommerceplatform.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserRepo extends JpaRepository<User, Integer> {
   User findByEmailandPassword(@RequestParam("email") String email, @RequestParam("password") String password);

    }
