package com.twilight.eCommercePlatform.restController;

import com.twilight.eCommercePlatform.dto.auth.PasswordUpdateRequest;
import com.twilight.eCommercePlatform.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid PasswordUpdateRequest passwordUpdateRequest){
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(passwordUpdateRequest.getEmail(), passwordUpdateRequest.getOldPassword()));
        if(authentication.isAuthenticated()){
            return new ResponseEntity<>(userService.updatePassword(passwordUpdateRequest.getNewPassword(), passwordUpdateRequest.getEmail()));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
