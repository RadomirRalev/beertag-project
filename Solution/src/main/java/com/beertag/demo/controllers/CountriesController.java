package com.beertag.demo.controllers;

import com.beertag.demo.helpers.BeersCollectionHelper;
import com.beertag.demo.models.Country;
import com.beertag.demo.services.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountriesController {
    private CountryService service;
    private Country country;

    public CountriesController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable int id) {
        return service.getCountryById(id);
    }

    @GetMapping
    public List<Country> getCountriesList() {
        List result = service.getCountriesList();
        //result = BeersCollectionHelper.sortBeersList(result, "name");
        return result;
    }

    @GetMapping("/search")
    @ResponseBody
    public Country getSpecificCountry(@RequestParam(defaultValue = "test") String name) {
        return service.getSpecificCountry(name);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable int id, @RequestBody Country country) {
        return service.update(id, country);
    }

    @PostMapping
    public void createCountry(Country newCountry) {
        service.createCountry(newCountry);
    }

    @DeleteMapping("{name}")
    public void deleteCountry(@PathVariable String name) {
        service.deleteCountry(name);
    }
}
