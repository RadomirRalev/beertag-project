package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Brewery;
import com.beertag.demo.services.BreweryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/breweries")
public class BreweryController {
    private BreweryService service;

    public BreweryController(BreweryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Brewery getById(@PathVariable int id) {
        try {
            return service.getBreweryById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public List<Brewery> getBreweriesList() {
        try {
            return service.getBreweriesList();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    @ResponseBody
    public Brewery getSpecificBrewery(@RequestParam(defaultValue = "test") String name) {
        try {
            return service.getSpecificBrewery(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Brewery update(@PathVariable int id, @RequestBody Brewery brewery) {
        try {
            return service.update(id, brewery);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Brewery createBrewery(@RequestBody Brewery newBrewery) {
        try {
            service.createBrewery(newBrewery);
            return newBrewery;
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("{name}")
    public void deleteBrewery(@PathVariable String name) {
        try {
            service.deleteBrewery(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
