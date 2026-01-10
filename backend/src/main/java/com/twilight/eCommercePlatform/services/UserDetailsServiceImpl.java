package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.entities.User;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        if(user!= null){
            return new UserDetailsImpl(user);
        }
        else{
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }
}
