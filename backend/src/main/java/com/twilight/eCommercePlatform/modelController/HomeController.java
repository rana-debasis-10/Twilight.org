package com.twilight.eCommercePlatform.modelController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(){
        return "forward:pages/index.html";
    }
    @GetMapping("/signIn")
    public String signIn(){
        return "forward:/pages/login.html";
    }
    @GetMapping("/signUp")
    public String signUp(){
        return "forward:/pages/signup.html";
    }

}
