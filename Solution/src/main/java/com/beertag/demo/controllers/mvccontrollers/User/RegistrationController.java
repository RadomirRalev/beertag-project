package com.beertag.demo.controllers.mvccontrollers.User;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.exceptions.InvalidOptionalFieldParameter;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistrationDTO;
import com.beertag.demo.models.user.UserUpdateDTO;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.beertag.demo.exceptions.Constants.*;
import static com.beertag.demo.helpers.UserHelper.currentPrincipalName;

import javax.validation.Valid;
import java.io.IOException;


@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("UserRegistration", new UserRegistrationDTO());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("UserRegistration") UserRegistrationDTO userRegistrationDTO,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "users/register";
        }

        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getPasswordConfirmation())) {
            model.addAttribute("error", PASSWORD_DO_NOT_MATCH);
            return "users/register";
        }

        try {
            userService.createUser(userRegistrationDTO);
        } catch (InvalidAgeException | InvalidOptionalFieldParameter | DuplicateEntityException | IOException e) {
            model.addAttribute("error", e.getMessage());
            return "users/register";
        }

        return "users/register-confirmation";
    }

    @GetMapping("/register-confirmation")
    public String showRegisterConfirmation() {
        return "redirect:/login";
    }

}
