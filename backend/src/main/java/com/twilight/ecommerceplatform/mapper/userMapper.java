package com.twilight.ecommerceplatform.mapper;

import com.twilight.ecommerceplatform.DataToObjects.UserDTO;
import com.twilight.ecommerceplatform.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "Spring")
public interface userMapper {


    @Mapping(target = "userId", ignore = true)
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);

    void updateUserfromUserDTO(UserDTO userDTO, @MappingTarget User user);
}
