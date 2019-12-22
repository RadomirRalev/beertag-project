package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
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

    @GetMapping("/{breweryId}/beers")
    public List<Beer> getBeersByBreweryId(@PathVariable int breweryId) {
        try {
            return service.getBeersByBreweryId(breweryId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Brewery> getBreweryByName(@RequestParam(defaultValue = "test") String name) {
        try {
            return service.getBreweryByName(name);
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
            return service.createBrewery(newBrewery);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void deleteBrewery(@PathVariable int id) {
        try {
            service.deleteBrewery(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
