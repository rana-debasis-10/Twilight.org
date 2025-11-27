package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.componenets.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionUser")
public class UserController {
    public SessionUser sessionUser() {
        return new SessionUser();
    }
}
