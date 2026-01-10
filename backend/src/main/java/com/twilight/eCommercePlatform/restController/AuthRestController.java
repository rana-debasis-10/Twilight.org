package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.auth.LoginRequestDTO;
import com.twilight.eCommercePlatform.dto.entity.UserDTO;
import com.twilight.eCommercePlatform.dto.auth.PasswordUpdateDTO;
import com.twilight.eCommercePlatform.entities.User;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import com.twilight.eCommercePlatform.services.JwtService;
import com.twilight.eCommercePlatform.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private  UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    /// Register
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid UserDTO userDTO){
        try {
            User user= userService.saveAsUser(userDTO);
            String token = jwtService.generateToken(new UserDetailsImpl(user));
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

    }


    /// Login
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.generateToken((UserDetailsImpl)authentication.getPrincipal()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /// Update Password
    @PostMapping("/updatePassword")
    public ResponseEntity<?>updatePassword(@RequestBody @Valid PasswordUpdateDTO passwordUpdateDTO){
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(passwordUpdateDTO.getEmail(),passwordUpdateDTO.getOldPassword()));
        if(authentication.isAuthenticated()){
            return new ResponseEntity<>(userService.updatePassword(passwordUpdateDTO.getNewPassword(),passwordUpdateDTO.getEmail()));
        }
       return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
