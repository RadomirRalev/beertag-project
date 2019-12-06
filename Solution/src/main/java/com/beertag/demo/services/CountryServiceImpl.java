package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Country;
import com.beertag.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country getCountryById(int id) {
        return countryRepository.getCountryById(id);
    }

    @Override
    public List<Country> getCountriesList() {
        return countryRepository.getCountriesList();
    }

    @Override
    public Country getSpecificCountry(String name) {
        try {
            return countryRepository.getSpecificCountry(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException (String.format("Country %s not found in the database", name));
        }
    }

    @Override
    public Country update(int id, Country country) {
        countryRepository.update(id, country);
        return country;
    }

    @Override
    public void createCountry(Country newCountry) {
        if (countryRepository.checkCountryExists(newCountry.getName())) {
            throw new DuplicateEntityException("Country", newCountry.getName());
        }
        countryRepository.createCountry(newCountry);
    }

    @Override
    public void deleteCountry(String name) {
        if (countryRepository.checkCountryExists(name)) {
            throw new EntityNotFoundException(String.format("Country %s does not exist.", name));
        }
        countryRepository.deleteCountry(name);
    }
}
