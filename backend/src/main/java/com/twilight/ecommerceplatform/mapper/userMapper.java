package com.twilight.ecommerceplatform.mapper;

import com.twilight.ecommerceplatform.DataToObjects.UserDTO;
import com.twilight.ecommerceplatform.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface userMapper {
    User toUserDTO(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
