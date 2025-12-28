package com.twilight.ecommerceplatform.services;

import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public User getUser(long userId){
        return userRepo.findById(userId).orElse(null);
    }
    public List<User> getUsers(List<Long> userIds){
        return userRepo.findAllById(userIds);
    }
    public Address getUserAddress(long userId){
        return userRepo.findAddressByUserId(userId);
    }
    public void saveUser(User user){
        userRepo.save(user);
    }

}
