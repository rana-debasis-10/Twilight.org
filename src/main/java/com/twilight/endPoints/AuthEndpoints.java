package com.twilight.endPoints;

import com.twilight.utils.annotations.MobileNumber;
import com.twilight.dataTransferObjects.Jwt;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.services.JwtService;
import com.twilight.services.MessageService;
import com.twilight.types.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
@AllArgsConstructor
public class AuthEndpoints {
    
    private MessageService messageService;
    
    private JwtService jwtService;

    @GetMapping("/login")
    @Transactional
    void login(@RequestParam(name = "m",required = true) @MobileNumber @NotBlank String mobNo){
        messageService.sendOtp(mobNo);
    };

    @GetMapping ("/verify")
    @Transactional
    Jwt verify(
            @RequestParam(name = "m") @MobileNumber @NotBlank String mobNo,
            @RequestParam(name = "o") Integer otp) throws UnAuthorizedException {
        Long lifespan = (long)600000;
        if(messageService.verifyOtp(mobNo, otp)){
                String token = jwtService.generateToken(mobNo, Role.verified, lifespan);
                return new Jwt(token);
        }
        else
                throw new UnAuthorizedException("User is trying to giving wrong OTP","OTP not matching");

    }



}
