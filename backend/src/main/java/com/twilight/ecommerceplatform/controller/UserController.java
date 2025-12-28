package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.dto.UserDTO;
import com.twilight.ecommerceplatform.componenets.SessionUser;
import com.twilight.ecommerceplatform.entities.User;
import com.twilight.ecommerceplatform.enums.UserRole;
import com.twilight.ecommerceplatform.repositories.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("sessionUser")
public class UserController {
    public SessionUser sessionUser() {return new SessionUser();}
    @Autowired
    UserRepo userRepo;

    @PostMapping("/signUp")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, HttpSession session){
        return ResponseEntity.ok().build();
    }

//Login Process
    @PostMapping("/signIn") //path to be declared
    public String signIn(@RequestParam("id") long id, @RequestParam("password") String password, HttpSession session, ModelMap model) {
    User user = userRepo.findByUserId((id));
    if (user != null) {
        UserRole role=user.getRole();
        //redirect to home page
        if (role == UserRole.CUSTOMER) {

            session.setAttribute("sessionUser", user); // login successful

        }
        return ""; //Index
    }
    else {
        model.put("message", "Invalid user id");
        return "sign-in";
    }

}

//Logging Out of the session
    @GetMapping("/logout") // path to be declared
    public String logout(HttpSession session) {
        session.removeAttribute("sessionUser");
        return "index";
    }
}

