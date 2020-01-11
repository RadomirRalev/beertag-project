package com.beertag.demo.controllers.mvccontrollers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.exceptions.InvalidOptionalFieldParameter;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistration;
import com.beertag.demo.models.user.UserUpdateDTO;
import com.beertag.demo.services.BeerService;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.beertag.demo.exceptions.Constants.*;

import javax.validation.Valid;
import java.io.IOException;


@Controller
public class RegistrationController {

    private UserService userService;
    private BeerService beerService;

    @Autowired
    public RegistrationController(UserService userService, BeerService beerService) {
        this.beerService = beerService;
        this.userService = userService;

    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("UserRegistration", new UserRegistration());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("UserRegistration") UserRegistration userRegistration,
                               BindingResult bindingResult,
                               Model model) {


        if (bindingResult.hasErrors()) {
            return "users/register";
        }

        if (!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())) {
            model.addAttribute("error", PASSWORD_DO_NOT_MATCH);
            return "users/register";
        }

        try {
            userService.createUser(userRegistration);
        } catch (InvalidAgeException | InvalidOptionalFieldParameter | DuplicateEntityException | IOException e) {
            model.addAttribute("error", e.getMessage());
            return "users/register";
        }

        return "users/register-confirmation";
    }

    @GetMapping("user/update")
    public String showUpdatePage(Model model) {
        model.addAttribute("UserUpdateDTO", new UserUpdateDTO());
        return "users/update";
    }

    @PostMapping("user/update")
    public String updateUser(@Valid @ModelAttribute("UserUpdateDTO") UserUpdateDTO userUpdateDTO,
                             @RequestParam("file") MultipartFile file,
                             BindingResult bindingResult, Model model) {
        model.addAttribute("file", file);
        if (bindingResult.hasErrors()) {
            return "users/update";
        }


        return "users/register-confirmation";
    }


    //TODO
    @GetMapping("/register-confirmation")
    public String showRegisterConfirmation() {
        return "redirect:/login";
    }


    @GetMapping("/profile")
    public String profile(Model model) {
        Beer beer = beerService.getById(26);
        model.addAttribute("beer", beer);

        return "profile";
    }

}
