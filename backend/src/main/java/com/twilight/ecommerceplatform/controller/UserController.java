package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.DataToObjects.LoginRequestDTO;
import com.twilight.ecommerceplatform.DataToObjects.LoginResponseDTO;
import com.twilight.ecommerceplatform.DataToObjects.UserDTO;
import com.twilight.ecommerceplatform.componenets.SessionUser;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import com.twilight.ecommerceplatform.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;

    }

    //Register
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO,@RequestParam String password, HttpSession session){
        UserDTO newUser= userService.saveUser(userDTO, password);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }


    //Login

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }
}

