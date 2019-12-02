package com.beertag.demo.controllers;

import com.beertag.demo.models.Beers;
import com.beertag.demo.services.BeersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeersController {
    private BeersService service;

    @Autowired
    public BeersController(BeersService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beers> getBeersList() {
        return service.getBeersList();
    }

    @GetMapping("/search")
    @ResponseBody
    public Beers getSpecificBeer(@RequestParam(defaultValue = "test") String name) {
        return service.getSpecificBeer(name);
    }

    @GetMapping("/filter")
    @ResponseBody
    public List<Beers> filterBeers(@RequestParam String filterType, @RequestParam String filterQuery) {
        return service.filterBeers(filterType, filterQuery);
    }

    @PostMapping
    public void createBeer(@RequestBody @Valid Beers newBeer) {
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
