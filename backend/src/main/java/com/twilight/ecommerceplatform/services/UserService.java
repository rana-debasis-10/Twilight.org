package com.twilight.ecommerceplatform.services;

import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    /// //////////// Password Encoder ////////////////
    @Autowired
    private PasswordEncoder passwordEncoder;

    /// /////////// Service and Repositories /////////////
    /// User Repository
    @Autowired
    private UserRepo userRepo;

    /// Address Service
    private AddressService addressService;

    ///////////////// Methods ///////////////////
    /// Get user by user ID
    public User getById(long userId){
        return userRepo.findById(userId).orElse(null);
    }

    /// Get all user by list of user ID-s
    public List<User> getALl(List<Long> userIds){
        return userRepo.findAllById(userIds);
    }

    /// Get address of user ID
    public Address getAddress(long userId){
        return addressService.getByUserId(userId);
    }

    /// Update/Save user
    public void saveUser(User user){
        userRepo.save(user);
    }

    /// Update password
    public boolean updatePassword(long userID,String oldPassword,String newPassword){
        Optional<User> user = userRepo.findById(userID);
        if(user.isPresent()){
            if(user.get().getPassword().equals(passwordEncoder.encode(oldPassword))) {
                user.get().setPassword(passwordEncoder.encode(newPassword));
                userRepo.save(user.get());
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    /// Login User Procedure
    public ResponseEntity<String> Login(){return null;}
}
