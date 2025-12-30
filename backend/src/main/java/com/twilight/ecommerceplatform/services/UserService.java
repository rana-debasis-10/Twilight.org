package com.twilight.ecommerceplatform.services;

import com.twilight.ecommerceplatform.dto.LoginRequestDTO;
import com.twilight.ecommerceplatform.dto.LoginResponseDTO;
import com.twilight.ecommerceplatform.dto.UserDTO;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.enums.UserRole;
import com.twilight.ecommerceplatform.mapper.UserMapper;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    public User getUser(long userId){

        return userRepo.findById(userId).orElse(null);
    }
    public List<UserDTO> getAllUsers(Long userId) {

        User admin = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (admin.getRole() != UserRole.ADMIN) {
            throw new RuntimeException("Access denied: Admins only");
        }

        return userRepo.findAll()
                .stream()
                .map(userMapper::toUserDTO)
                .toList();
    }


    //Register User
    public UserDTO saveUser(UserDTO userDTO){
        User user=userMapper.toUser(userDTO);
        return userMapper.toUserDTO(userRepo.save(user));
    }
    public void saveAsUser(User user){
        userRepo.save(user);
    }

    //User Login
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        User user = userRepo.findByEmailAndRole(loginRequestDTO.getEmail(), loginRequestDTO.getRole());


        if (!user.getPassword().equals(loginRequestDTO.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setUserId(user.getUserId());
        loginResponse.setUsername(user.getName());
        loginResponse.setUserRole(user.getRole().toString());

        //Return view

        switch (user.getRole()){
            case ADMIN -> loginResponse.setView("admin");
            case RESTAURENT_OWNER ->  loginResponse.setView("restaurantOwner");
            case CUSTOMER ->  loginResponse.setView("customer");
        }

        return loginResponse;
    }

}
