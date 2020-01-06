package com.beertag.demo.controllers.restcontrollers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.*;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/wishlist/{userId}")
    public Set<Beer> getWishList(@PathVariable int userId) {
        try {
            return userService.getWishList(userId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/wishlist/{userId}/{beerId}")
    public void addBeerToWishList(@PathVariable int userId, @PathVariable int beerId) {
        userService.addBeerToWishList(userId, beerId);
    }

    @GetMapping("/dranklist/{userId}")
    public Set<Beer> getDrankList(@PathVariable int userId) {
        try {
            return userService.getDrankList(userId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/dranklist/{userId}/{beerId}/{rating}")
    public void addBeerToDrankList(@PathVariable int userId, @PathVariable int beerId, @PathVariable int rating) {
        userService.addBeerToDrankList(userId, beerId, rating);
    }

    @GetMapping
    public List<UserDetail> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/username/{username}")
    public UserDetail getByUsername(@PathVariable String username) {
        try {
            return userService.getByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    @GetMapping("/id/{id}")
    public UserDetail getById(@PathVariable int id) {
        try {
            return userService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public UserDetail create(@RequestBody @Valid UserRegistration userRegistration) {
        try {
            return userService.createUser(userRegistration);
        } catch (DuplicateEntityException | InvalidAgeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public UserDetail updateUser(@PathVariable int id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        UserDetail userDetailToUpdate = getById(id);
        mapper.validationData(userUpdateDTO, userDetailToUpdate);
        return userService.updateUser(userDetailToUpdate);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        UserDetail userDetailToDelete = getById(id);
        userService.softDeleteUser(userDetailToDelete);

    }

}
