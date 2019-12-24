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
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;
    private UserUpdateMapper updateMapper;


    @Autowired
    public UserController(UserService beerTagServices, UserUpdateMapper updateMapper) {
        this.userService = beerTagServices;
        this.updateMapper = updateMapper;
    }

    @GetMapping
    public Collection<User> showUsers() {
        return userService.showUsers();
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
        User user = getById(id);
        updateMapper.validationData(userUpdateDTO, user);
        try {
            return userService.updateUser(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping
    public void delete(@RequestBody User user) {
        try {
            userService.deleteUser(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/nickname/{Nickname}")
    public List<User> getById(@PathVariable String Nickname) {
        return userService.getByNickname(Nickname);
    }

    @GetMapping("/id/{id}")
    public User getById(@PathVariable int id) {
        try {
            return userService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
