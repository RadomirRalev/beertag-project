package com.beertag.demo.controllers;

import com.beertag.demo.helpers.BeersCollectionHelper;
import com.beertag.demo.models.BeerDto;
import com.beertag.demo.models.Beers;
import com.beertag.demo.models.DtoMapper;
import com.beertag.demo.services.BeersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestParam (required = false) String abvTag) {
        List<Beers> result = service.getBeersList();
        result = BeersCollectionHelper.filterByName(result, name);
        result = BeersCollectionHelper.filterByStyle(result, style);
        return result;
    }

    @GetMapping("/search")
    @ResponseBody
    public Beers getSpecificBeer(@RequestParam(defaultValue = "test") String name) {
        return service.getSpecificBeer(name);
    }

    @PutMapping("/{id}")
    public Beers update(@PathVariable int id, @RequestBody @Valid BeerDto beerDto) {
            Beers beerToBeUpdated = mapper.fromDto(beerDto);
            return service.update(id, beerToBeUpdated);
    }

    @PostMapping
    public void createBeer(@RequestBody @Valid BeerDto newBeerDto) {
        Beers newBeer = mapper.fromDto(newBeerDto);
        service.createBeer(newBeer);
    }

    @DeleteMapping("deletebeer/{name}")
    public void deleteBeer(@PathVariable String name) {
        service.deleteBeer(name);
    }

    @GetMapping("/sort")
    public List<Beers> sortEntries(@RequestParam String sortType) {
        return service.sortEntries(sortType);
    }
}