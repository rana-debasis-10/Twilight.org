package com.twilight.eCommercePlatform.mapper;

import com.twilight.eCommercePlatform.dto.entity.UserDTO;
import com.twilight.eCommercePlatform.entities.User;
import com.twilight.eCommercePlatform.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user){
        if ( user == null ) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName( user.getName() );
        userDTO.setMobNo( user.getMobNo() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );

        return userDTO;
    };
    public User toUser(UserDTO dto){
        if ( dto == null ) {
            return null;
        }
        User user = new User();
        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );
        user.setMobNo( dto.getMobNo() );
        user.setPassword( dto.getPassword());
        user.setRole(UserRole.CUSTOMER);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        return user;
    };
}
