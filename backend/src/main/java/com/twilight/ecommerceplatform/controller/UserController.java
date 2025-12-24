package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.componenets.SessionUser;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionUser")
public class UserController {
    public SessionUser sessionUser() {return new SessionUser();}
    @Autowired
    UserRepo userRepo;
    @PostMapping
        public String signUp(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("name")String name){

            User user = new User();
            userRepo.save(user);
            return "Sign up successfully";
        }

    @PostMapping("") //path to be declared
        public String signIn(@RequestParam("email") String email, @RequestParam("password") String password) {
            User user = userRepo.findByEmail(email);
            if (user != null) {

            }
            else{

            }
            return "";
        }
    }

