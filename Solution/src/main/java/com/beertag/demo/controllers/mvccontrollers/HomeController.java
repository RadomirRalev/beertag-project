package com.beertag.demo.controllers.mvccontrollers;

import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.beertag.demo.helpers.UserHelper.currentPrincipalName;

@Controller("/")
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showHomePage(Model model) {
        if (!currentPrincipalName().equals("anonymousUser")) {
            model.addAttribute("user", userService.getByUsername(currentPrincipalName()));
        }
        return "index";
    }

}


