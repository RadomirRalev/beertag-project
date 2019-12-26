package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Rating;
import com.beertag.demo.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/rating")
public class RatingController {
    private RatingService service;

    public RatingController(RatingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Rating getRating(@PathVariable int id) {
        try {
            return service.getRating(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void createRating(@RequestBody Rating newRating) {
        try {
            service.createRating(newRating);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Rating ratingToUpdate) {
        try {
            service.updateRating(id, ratingToUpdate);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
