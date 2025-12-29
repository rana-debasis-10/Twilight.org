package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.dto.LoginRequestDTO;
import com.twilight.ecommerceplatform.dto.LoginResponseDTO;
import com.twilight.ecommerceplatform.dto.UserDTO;
import com.twilight.ecommerceplatform.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    //Admin only

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam Long adminId) {

        return ResponseEntity.ok(userService.getAllUsers(adminId));
    }


}

