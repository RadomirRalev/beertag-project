package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.helpers.BeersCollectionHelper;
import com.beertag.demo.models.BeerDto;
import com.beertag.demo.models.Beers;
import com.beertag.demo.models.DtoMapper;
import com.beertag.demo.services.BeersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeersController {
    private BeersService service;
    private DtoMapper mapper;

    @Autowired
    public BeersController(BeersService service, DtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public Beers getById(@PathVariable int id) {
            return service.getById(id);
    }

    @GetMapping
    public List<Beers> getBeersList(@RequestParam (required = false) String name,
                                    @RequestParam (required = false) String style,
                                    @RequestParam (required = false) String sortType) {
        List<Beers> result = service.getBeersList();
        result = BeersCollectionHelper.filterByName(result, name);
        result = BeersCollectionHelper.filterByStyle(result, style);
        result = BeersCollectionHelper.sortBeersList(result, sortType);
        return result;
    }

    @GetMapping("/search")
    @ResponseBody
    public Beers getSpecificBeer(@RequestParam(defaultValue = "test") String name) {
        try {
            return service.getSpecificBeer(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beers update(@PathVariable int id, @RequestBody @Valid BeerDto beerDto) {
        try {
            Beers beerToBeUpdated = mapper.fromDto(beerDto);
            return service.update(id, beerToBeUpdated);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void createBeer(@RequestBody @Valid BeerDto newBeerDto) {
        try {
            Beers newBeer = mapper.fromDto(newBeerDto);
            service.createBeer(newBeer);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("deletebeer/{name}")
    public void deleteBeer(@PathVariable String name) {
        try {
            service.deleteBeer(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}