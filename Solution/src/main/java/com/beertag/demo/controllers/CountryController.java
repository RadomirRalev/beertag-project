package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Country;
import com.beertag.demo.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountryController {
    private CountryService service;
    private Country country;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable int id) {
        try {
            return service.getCountryById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public List<Country> getCountriesList() {
        try {
            return service.getCountriesList();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    @ResponseBody
    public Country getSpecificCountry(@RequestParam(defaultValue = "test") String name) {
        try {
            return service.getSpecificCountry(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable int id, @RequestBody Country country) {
        try {
            return service.update(id, country);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Country createCountry(@RequestBody Country newCountry) {
        try {
            return service.createCountry(newCountry);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("{name}")
    public void deleteCountry(@PathVariable String name) {
        try {
            service.deleteCountry(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
