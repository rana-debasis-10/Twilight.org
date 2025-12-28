package com.twilight.ecommerceplatform.repositories;

import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
        public User findByUserId(long UserId);

        public Address findAddressById(long userId);

        public Role findRoleById(long userId);

        Optional<User> findByEmailAndRole(String email, String role);
    User findByUserId(Long userId);
}
