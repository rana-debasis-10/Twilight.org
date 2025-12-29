package com.twilight.ecommerceplatform.services;

import com.twilight.ecommerceplatform.DataToObjects.LoginRequestDTO;
import com.twilight.ecommerceplatform.DataToObjects.LoginResponseDTO;
import com.twilight.ecommerceplatform.DataToObjects.UserDTO;
import com.twilight.ecommerceplatform.entities.Address;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.enums.UserRole;
import com.twilight.ecommerceplatform.mapper.userMapper;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    private userMapper  userMapper;
    private RequestAttributes requestAttributes;

    public UserService(userMapper userMapper, UserRepo userRepo) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }

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
    public Address getUserAddress(long userId){
        return userRepo.findAddressById(userId);
    }

    //Register User
    public UserDTO saveUser(UserDTO userDTO, String Password){
        User user=userMapper.toUser(userDTO);
        user.setPassword(Password);
        return userMapper.toUserDTO(userRepo.save(user));
    }

    //User Login
   public LoginResponseDTO login(LoginRequestDTO  loginRequestDTO){
        User user = userRepo.findByEmailAndRole(loginRequestDTO.getEmail(), loginRequestDTO.getRole()).orElseThrow(()-> new RuntimeException("Invalid email or password"));


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
