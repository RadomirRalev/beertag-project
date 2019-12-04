package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.User;
import com.beertag.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserServices beerTagServices;

    @Autowired
    public UserController(UserServices beerTagServices) {
        this.beerTagServices = beerTagServices;
    }

    @GetMapping
    public Collection<User> showUsers() {
        return beerTagServices.showUsers();
    }

    @PostMapping
    public User create(@RequestBody @Valid User user) {
        try {
            beerTagServices.createUser(user);
            return user;
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    //TODO дали не трябва да хвърля грешка ако го update със същите данни ?
    @PutMapping
    public User updateUser (@RequestBody @Valid User user){
        try {
            beerTagServices.updateUser(user);
            return user;
        }
        catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping
    public void delete (@RequestBody User user){
        try {
            beerTagServices.deleteUser(user);
        }
        catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
