package com.twilight.eCommercePlatform.mapper;

import com.twilight.eCommercePlatform.dto.entity.UserDTO;
import com.twilight.eCommercePlatform.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    @Mapping(target = "id", ignore = true)       // DB generated
    @Mapping(target = "orders", ignore = true)   // Not part of DTO
    User toUser(UserDTO dto);
}
