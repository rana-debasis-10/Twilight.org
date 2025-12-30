package com.twilight.ecommerceplatform.mapper;

import com.twilight.ecommerceplatform.dto.UserDTO;
import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {

    // Entity → DTO
    public default UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();

        dto.setUserId(user.getUserId() != null ? user.getUserId().toString() : null);
        dto.setName(user.getName());
        dto.setMobNo(user.getMobNo());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPassword(user.getPassword());

        // FIXED expression
        dto.setAddress(
                user.getAddress() != null ? user.getAddress().toString() : null
        );

        return dto;
    }

    // DTO → Entity
    public default User toUser(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        // FIXED expression
        user.setUserId(
                dto.getUserId() != null ? Long.parseLong(dto.getUserId()) : null
        );

        user.setName(dto.getName());
        user.setMobNo(dto.getMobNo());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());

        // FIXED expression
        user.setAddress(
                dto.getAddress() != null ? new Address() : null
        );

        return user;
    }
}
