package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.dto.LoginRequestDTO;
import com.twilight.ecommerceplatform.dto.LoginResponseDTO;
import com.twilight.ecommerceplatform.dto.UserDTO;
import com.twilight.ecommerceplatform.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("sessionUser")

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private  UserService userService;


    //Register
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO){
        UserDTO newUser= userService.saveUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }


    //Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }

    //Admin only

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam Long adminId) {

        return ResponseEntity.ok(userService.getAllUsers(adminId));
    }
}
