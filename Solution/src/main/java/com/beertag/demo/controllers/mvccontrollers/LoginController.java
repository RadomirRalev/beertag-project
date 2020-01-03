package com.beertag.demo.controllers.mvccontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login1")
    public String showBeers(Model model) {
        return "login1";
    }
}
