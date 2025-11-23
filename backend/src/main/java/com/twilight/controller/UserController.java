package com.twilight.controller;

import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public abstract class UserController {
    @Autowired
    UserRepo userRepo;
    @PostMapping
    public String signUp(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("name")String name){


        UserRepo.save();
        return "Sign up successfully";
    }

    @PostMapping("") //path to be declared
    public String signIn(@RequestParam("email") String email, @RequestParam("password") String password) {
        return null;
    }

}
