package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.helpers.BeerCollectionHelper;
import com.beertag.demo.models.BeerDto;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.DtoMapper;
import com.beertag.demo.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerController {
    private BeerService service;
    private DtoMapper mapper;

    @Autowired
    public BeerController(BeerService service, DtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public List<Beer> getBeersList(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String style,
                                   @RequestParam(required = false) String sortType) {
        List<Beer> result = service.getBeersList();
        result = BeerCollectionHelper.filterByName(result, name);
        result = BeerCollectionHelper.filterByStyle(result, style);
        result = BeerCollectionHelper.sortBeersList(result, sortType);
        return result;
    }

    @GetMapping("/search")
    @ResponseBody
    public Beer getSpecificBeer(@RequestParam(defaultValue = "test") String name) {
        try {
            return service.getSpecificBeer(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable int id, @RequestBody @Valid BeerDto beerDto) {
        try {
            Beer beerToBeUpdated = mapper.fromDto(beerDto);
            return service.update(id, beerToBeUpdated);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer createBeer(@RequestBody @Valid BeerDto newBeerDto) {
        try {
            Beer newBeer = mapper.fromDto(newBeerDto);
            return service.createBeer(newBeer);
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