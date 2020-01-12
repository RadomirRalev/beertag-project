package com.beertag.demo.controllers.mvccontrollers.User;

import com.beertag.demo.exceptions.InvalidOptionalFieldParameter;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserUpdateDTO;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

import static com.beertag.demo.helpers.UserHelper.currentPrincipalName;

@Controller
public class UpdateController {

    private UserService userService;

    @Autowired
    public UpdateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        User user = userService.getByUsername(currentPrincipalName());
        model.addAttribute("user", user);
        model.addAttribute("userUpdateDTO", userUpdateDTO);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO,
                                BindingResult bindingResult, Model model) {

        User user = userService.getByUsername(currentPrincipalName());
        if (bindingResult.hasErrors()) {
            return "redirect:";
        }
        try {
            userService.updateUser(user, userUpdateDTO);
        } catch (InvalidOptionalFieldParameter | IOException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/profile";
        }
        return "/users/success-update";
    }


}