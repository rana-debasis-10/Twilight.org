package com.twilight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @GetMapping("/")
    public String home(){
        return "Home";
    }
    @GetMapping("sign")
    public String sign(){
        return "sign";
    }

}
