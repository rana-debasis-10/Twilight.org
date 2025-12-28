package com.twilight.ecommerceplatform.mapper;

import com.twilight.ecommerceplatform.dto.UserDTO;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.enums.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // DTO → Entity
    @Mapping(target = "userId", expression = "java(dto.getUserId() != null ? Long.parseLong(dto.getUserId()) : null)")
    @Mapping(target = "role", expression = "java(dto.getRole() != null ? UserRole.valueOf(dto.getRole()) : null)")
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User toEntity(UserDTO dto);

    // Entity → DTO
    @Mapping(target = "userId", expression = "java(user.getUserId() != null ? user.getUserId().toString() : null)")
    @Mapping(target = "role", expression = "java(user.getRole() != null ? user.getRole().name() : null)")
    @Mapping(target = "address", expression = "java(user.getAddress() != null ? user.getAddress().toString() : null)")
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);

    // UPDATE existing entity
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "address", ignore = true)
    void updateEntityFromDTO(UserDTO dto,
                             @MappingTarget User user);
}
