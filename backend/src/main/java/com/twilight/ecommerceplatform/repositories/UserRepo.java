package com.twilight.ecommerceplatform.repositories;

import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
        public User findByUserId(long UserId);
        public Address findAddressByUserId(long userId);
        public User findByEmailAndRole(String email, UserRole role);
}
