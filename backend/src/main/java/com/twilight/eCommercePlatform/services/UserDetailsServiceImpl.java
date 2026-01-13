package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.entities.User;
import com.twilight.eCommercePlatform.repositories.RoleRepo;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        if(user!= null){
            return new UserDetailsImpl(user,roleRepo.findByUserRole(user.getRole()).getAuthorities());
        }
        else{
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }
}
