package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.models.user.RegistrationValidatorMapper;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistrationValidation;
import com.beertag.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserServices userServices;
    private RegistrationValidatorMapper mapper;

    @Autowired
    public UserController(UserServices beerTagServices, RegistrationValidatorMapper mapper) {
        this.userServices = beerTagServices;
        this.mapper = mapper;
    }

    @GetMapping
    public Collection<User> showUsers() {
        return userServices.showUsers();
    }

    @PostMapping
    public User create(@RequestBody @Valid UserRegistrationValidation userDto) {
        try {
            User user = mapper.fromDto(userDto);
            return userServices.createUser(user);
        } catch (DuplicateEntityException | InvalidAgeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user) {
        try {
            return userServices.updateUser(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping
    public User delete(@RequestBody User user) {
        try {
           return userServices.deleteUser(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
