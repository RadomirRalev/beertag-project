package com.beertag.demo.controllers;


import com.beertag.demo.models.User;
import com.beertag.demo.services.BeerTagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class userController {

    private BeerTagServices beerTagServices;

    @Autowired
    public userController(BeerTagServices beerTagServices) {
        this.beerTagServices = beerTagServices;
    }

    @PostMapping
    public User create(@RequestBody @Valid User user) {
        beerTagServices.createUser(user);
        return user;
    }




}
