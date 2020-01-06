package com.beertag.demo.controllers.mvccontrollers;

import com.beertag.demo.models.user.UserDetail;
import com.beertag.demo.models.user.UserRegistration;
import com.beertag.demo.repositories.UserRepository;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {

    private UserDetailsManager userDetailsManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userService;

    @Autowired
    public RegistrationController(UserDetailsManager userDetailsManager, UserRepository userService) {
        this.userDetailsManager = userDetailsManager;
        this.userService = userService;

    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("UserRegistration", new UserRegistration());
        return "users/register";

    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("UserRegistration") UserRegistration userRegistration, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("error", "Username/password can't be empty!");
            return "users/register";
        }

        if (userDetailsManager.userExists(userRegistration.getUsername())) {
            model.addAttribute("error", "User with the same username already exists!");
            return "users/register";
        }

//        if (!user.getPassword().equals(use.getPasswordConfirmation())) {
//            model.addAttribute("error", "Password does't match!");
//            return "register";
//        }
        UserDetail user = new UserDetail();
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setEmail(userRegistration.getEmail());
        user.setUsername(userRegistration.getUsername());


        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        userRegistration.getUsername(), "{noop}" + userRegistration.getPassword(),
                        authorities);
        userDetailsManager.createUser(newUser);
        userService.createUser(user);

        return "users/register-confirmation";
    }


    @GetMapping("/register-confirmation")
    public String showRegisterConfirmation() {
        return "users/register-confirmation";
    }
}
