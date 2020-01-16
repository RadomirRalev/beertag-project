package com.beertag.demo.controllers.mvccontrollers.User;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.InvalidOptionalFieldParameter;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserUpdateDTO;
import com.beertag.demo.services.UserService;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.Valid;
import java.io.IOException;

import static com.beertag.demo.helpers.UserHelper.currentPrincipalName;
import static com.beertag.demo.constants.SQLQueryConstants.*;


@Controller
public class UpdateController {

    private UserService userService;

    @Autowired
    public UpdateController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/update")
    public String profile( Model model) {
        String currentUser = currentPrincipalName();
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        model.addAttribute("user", userService.getByUsername(currentPrincipalName()));
        model.addAttribute("userUpdateDTO", userUpdateDTO);
        model.addAttribute("currentuser", currentUser);
        return "/users/update";
    }


    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO, Model model) {
        User user = userService.getByUsername(currentPrincipalName());

        try {
            userService.updateUser(user, userUpdateDTO);
        } catch (InvalidOptionalFieldParameter | IOException e) {
            model.addAttribute("currentuser", currentPrincipalName());
            model.addAttribute("user", userService.getByUsername(currentPrincipalName()));
            model.addAttribute("error", e.getMessage());
            return "/users/update";
        }
        return "/users/success-update";
    }

    @GetMapping("/update/{username}")
    public String profile(@PathVariable("username") String username, Model model) {
        String currentUser = currentPrincipalName();
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        User user = userService.getByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("userUpdateDTO", userUpdateDTO);
        model.addAttribute("currentuser", currentUser);

        return "/users/update";
    }


    @PostMapping("/update/{username}")
    public String updateProfileAdmin( @Valid @PathVariable("username") String username,
                                     @ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO, Model model) {
        User user = userService.getByUsername(username);

        try {
            userService.updateUser(user, userUpdateDTO);
        } catch (InvalidOptionalFieldParameter | IOException  e) {
            model.addAttribute("currentuser", currentPrincipalName());
            model.addAttribute("user", userService.getByUsername(currentPrincipalName()));
            model.addAttribute("error", e.getMessage());
            return "/users/update" + username;
        }
        return "/users/update" + username;
    }

    @PostMapping("/delete")
    public String deleteProfile() {
        userService.setStatusUser(currentPrincipalName(),DISABLE);
         SecurityContextHolder.clearContext();
        return "/users/success-delete";
    }

    @PostMapping("/delete/{username}")
    public String deleteProfileAdmin(@PathVariable("username") String username) {
        userService.setStatusUser(username,DISABLE);
        SecurityContextHolder.clearContext();
        return "/users/success-delete-admin";
    }

}
