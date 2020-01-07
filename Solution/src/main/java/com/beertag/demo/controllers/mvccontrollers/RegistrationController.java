package com.beertag.demo.controllers.mvccontrollers;

import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.exceptions.InvalidOptionalFieldParameter;
import com.beertag.demo.models.user.UserDetail;
import com.beertag.demo.models.user.UserRegistration;
import com.beertag.demo.repositories.UserRepository;
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

import static com.beertag.demo.models.user.UserMapper.validationData;
import static com.beertag.demo.exceptions.Constants.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {


    private UserDetailsManager userDetailsManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserDetailsManager userDetailsManager,
                                  PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("UserRegistration", new UserRegistration());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("UserRegistration") UserRegistration userRegistration,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "users/register";
        }

        if (userDetailsManager.userExists(userRegistration.getUsername())) {
            model.addAttribute("error", String.format(USER_USERNAME_EXISTS, userRegistration.getUsername()));
            return "users/register";
        }

        if (!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())) {
            model.addAttribute("error", PASSWORD_DO_NOT_MATCH);
            return "users/register";
        }


        UserDetail user = new UserDetail();

        try {
            user = validationData(userRegistration);
        } catch (InvalidAgeException | InvalidOptionalFieldParameter e) {
            model.addAttribute("error", e.getMessage());
            return "users/register";
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        userRegistration.getUsername(),
                        passwordEncoder.encode(userRegistration.getPassword()),
                        authorities);
        userDetailsManager.createUser(newUser);
        userRepository.createUser(user);

        return "users/register-confirmation";
    }


    @GetMapping("/register-confirmation")
    public String showRegisterConfirmation() {
        return "users/register-confirmation";
    }
}
