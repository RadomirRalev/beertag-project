package com.beertag.demo.controllers.mvccontrollers.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "users/access-denied";
    }


}
