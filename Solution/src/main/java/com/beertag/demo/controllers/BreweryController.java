package com.beertag.demo.controllers;

import com.beertag.demo.models.Brewery;
import com.beertag.demo.services.BreweryService;
import org.springframework.web.bind.annotation.*;

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
        return service.getBreweryById(id);
    }

    @GetMapping
    public List<Brewery> getBreweriesList() {
        return service.getBreweriesList();
    }

    @GetMapping("/search")
    @ResponseBody
    public Brewery getSpecificBrewery(@RequestParam(defaultValue = "test") String name) {
        return service.getSpecificBrewery(name);
    }

    @PutMapping("/{id}")
    public Brewery update(@PathVariable int id, @RequestBody Brewery brewery) {
        return service.update(id, brewery);
    }

    @PostMapping
    public void createBrewery(@RequestBody Brewery newBrewery) {
        service.createBrewery(newBrewery);
    }

    @DeleteMapping("{name}")
    public void deleteBrewery(@PathVariable String name) {
        service.deleteBrewery(name);
    }

}
