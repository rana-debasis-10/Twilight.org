package com.twilight.ecommerceplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/signIn")
    public String signIn(){
        return "sign";
    }
    @GetMapping("/signUp")
    public String signUp(){
        return "signUp";
    }

}
