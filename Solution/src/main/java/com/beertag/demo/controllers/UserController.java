package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.models.user.*;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;
    private UserMapper mapper;

    @Autowired
    public UserController(UserService beerTagServices, UserMapper mapper) {
        this.userService = beerTagServices;
        this.mapper = mapper;
    }

    @GetMapping
    public List<User> showUsers() {
        return userService.showUsers();
    }

    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable String username) {
        try {
            return userService.getByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    } 

    @GetMapping("/id/{id}")
    public User getById(@PathVariable int id) {
        try {
            return userService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public User create(@RequestBody @Valid UserRegistration userRegistration) {
        try {
            return userService.createUser(userRegistration);
        } catch (DuplicateEntityException | InvalidAgeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        User userToUpdate = getById(id);
        mapper.validationData(userUpdateDTO, userToUpdate);
        return userService.updateUser(userToUpdate);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        User userToDelete = getById(id);
        userService.softDeleteUser(userToDelete);

    }

}
