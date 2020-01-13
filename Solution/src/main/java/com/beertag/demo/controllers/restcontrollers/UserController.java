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
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService beerTagServices) {
        this.userService = beerTagServices;
    }

    @GetMapping("/wishlist/{username}")
    public Set<Beer> getWishList(@PathVariable String username) {

        return userService.getWishList(username);
    }

    @PostMapping("/wishlist/{username}/{beerId}")
    public void addBeerToWishList(@PathVariable String username, @PathVariable int beerId) {
        userService.addBeerToWishList(username, beerId);
    }

    @GetMapping("/dranklist/{username}")
    public Set<Beer> getDrankList(@PathVariable String username) {
        return userService.getDrankList(username);
    }

    @PostMapping("/dranklist/{username}/{beerId}/{rating}")
    public void addBeerToDrankList(@PathVariable String username, @PathVariable int beerId, @PathVariable int rating) {
        userService.addBeerToDrankList(username, beerId);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
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
    public User create(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        try {
            return userService.createUser(userRegistrationDTO);
        } catch (DuplicateEntityException | InvalidAgeException | IOException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody @Valid UserUpdateDTO userUpdateDTO)
            throws IOException {
        User userToUpdate = getById(id);
        return userService.updateUser(userToUpdate,userUpdateDTO);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        User userToDelete = getById(id);
        userService.softDeleteUser(userToDelete);
    }
}
