package com.beertag.demo.controllers.mvccontrollers.User;

import com.beertag.demo.models.user.User;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import static com.beertag.demo.helpers.UserHelper.*;


@Component
public class ProfileController {

    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }




}
