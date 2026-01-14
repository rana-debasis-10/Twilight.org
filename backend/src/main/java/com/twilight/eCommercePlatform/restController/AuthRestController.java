package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.auth.LoginRequest;
import com.twilight.eCommercePlatform.dto.entity.RestaurantDTO;
import com.twilight.eCommercePlatform.dto.entity.UserDTO;
import com.twilight.eCommercePlatform.dto.auth.PasswordUpdateRequest;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import com.twilight.eCommercePlatform.services.JwtService;
import com.twilight.eCommercePlatform.services.RestaurantService;
import com.twilight.eCommercePlatform.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
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
    @Autowired
    private RestaurantService restaurantService;


    /// Register
    @PostMapping("/user/register")
    @Validated
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        try {
            UserDetailsImpl user= userService.saveAsUser(userDTO);
            String token = jwtService.generateToken(user);
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

    }

    /// Login
    @PostMapping("/user/login")
    @Validated
    public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.generateToken((UserDetailsImpl)authentication.getPrincipal()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /// Register Restaurant with a User
    @PostMapping("/restaurant/register")
    @Validated
    public ResponseEntity<?> restaurantRegister(@RequestBody UserDTO userDTO, @RequestBody RestaurantDTO restaurantDTO){

        String token = (String)registerUser(userDTO).getBody();
        try{
            restaurantService.createRestaurant(restaurantDTO,userDTO.getEmail());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
