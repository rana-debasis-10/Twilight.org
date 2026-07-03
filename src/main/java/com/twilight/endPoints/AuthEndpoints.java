package com.twilight.endPoints;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.Jwt;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.services.JwtService;
import com.twilight.services.MessageService;
import com.twilight.types.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthEndpoints {
    @Autowired
    MessageService messageService;
    @Autowired
    JwtService jwtService;
    @GetMapping("/login")
    @Validated
    void login(@RequestParam(name = "m",required = true) @MobileNumber String mobNo){
        messageService.sendOtp(mobNo);
    };

    @PostMapping ("/verify")
    @Validated
    Jwt verify(
            @RequestParam(name = "m") @MobileNumber String mobNo,
            @RequestParam(name = "o")Integer otp) throws UnAuthorizedException {
        if(messageService.verifyOtp(mobNo, otp)){
                String token = jwtService.generateToken(mobNo, Role.undefined);
                System.out.println("\n Token : "+token);
                Jwt jwt = new Jwt(token);
                System.out.println("Token is  \n"+ jwt.getJwt());
                return jwt;
        }
        else
                throw new UnAuthorizedException("User is trying to giving wrong OTP","OTP not matching");

    }



}
