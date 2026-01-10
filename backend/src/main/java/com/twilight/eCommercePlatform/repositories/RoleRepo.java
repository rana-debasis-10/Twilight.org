package com.twilight.eCommercePlatform.repositories;

import com.twilight.eCommercePlatform.entities.Role;
import com.twilight.eCommercePlatform.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByUserRole(UserRole userRole);
}
