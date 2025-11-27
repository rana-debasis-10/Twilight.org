package com.twilight.ecommerceplatform.repositories;

import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
        public Address findAddressById(long userId);

}
