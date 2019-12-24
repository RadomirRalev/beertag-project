package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.models.user.UserDtoMapper;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserDto;
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
    private UserDtoMapper mapper;

    @Autowired
    public UserController(UserService beerTagServices, UserDtoMapper mapper) {
        this.userService = beerTagServices;
        this.mapper = mapper;
    }

    @GetMapping
    public Collection<User> showUsers() {
        return userService.showUsers();
    }

    @PostMapping
    public User create(@RequestBody @Valid UserDto userDto) {
        try {
            User user = mapper.validationData(userDto);
            return userService.createUser(user);
        } catch (DuplicateEntityException | InvalidAgeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user) {
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

    @GetMapping("/{Nickname}")
    public List<User> getByNickname(@PathVariable String Nickname) {
        return userService.getByNickname(Nickname);
    }

    @GetMapping("/id/{id}")
    public User getByNickname(@PathVariable int id) {
        try {
            return userService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
