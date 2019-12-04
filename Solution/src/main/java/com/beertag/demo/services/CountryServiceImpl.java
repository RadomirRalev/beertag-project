package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Country;
import com.beertag.demo.repositories.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private CountriesRepository countriesRepository;

    @Autowired
    public CountryServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public Country getCountryById(int id) {
        return countriesRepository.getCountryById(id);
    }

    @Override
    public List<Country> getCountriesList() {
        return countriesRepository.getCountriesList();
    }

    @Override
    public Country getSpecificCountry(String name) {
        try {
            return countriesRepository.getSpecificCountry(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException (String.format("Country %s not found in the database", name));
        }
    }

    @Override
    public Country update(int id, Country country) {
        countriesRepository.update(id, country);
        return country;
    }

    @Override
    public void createCountry(Country newCountry) {
        if (countriesRepository.checkCountryExists(newCountry.getName())) {
            throw new DuplicateEntityException("Country", newCountry.getName());
        }
        countriesRepository.createCountry(newCountry);
    }

    @Override
    public void deleteCountry(String name) {
        if (countriesRepository.checkCountryExists(name)) {
            throw new EntityNotFoundException(String.format("Country %s does not exist.", name));
        }
        countriesRepository.deleteCountry(name);
    }
}
