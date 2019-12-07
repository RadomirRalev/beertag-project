package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Country;
import com.beertag.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country getCountryById(int id) {
        try {
            return countryRepository.getCountryById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException (COUNTRY_ID_NOT_FOUND, id);
        }
    }

    @Override
    public List<Country> getCountriesList() {
        try {
            return countryRepository.getCountriesList();
        } catch (Exception e) {
            throw new EntityNotFoundException (LIST_EMPTY);
        }
    }

    @Override
    public Country getSpecificCountry(String name) {
        try {
            return countryRepository.getSpecificCountry(name);
        } catch (Exception ex) {
            throw new EntityNotFoundException (COUNTRY_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Country update(int id, Country country) {
        try {
            countryRepository.update(id, country);
            return country;
        } catch (Exception e) {
            throw new EntityNotFoundException (COUNTRY_ID_NOT_FOUND, id);
        }
    }

    @Override
    public Country createCountry(Country newCountry) {
        try {
            countryRepository.createCountry(newCountry);
            return newCountry;
        } catch (Exception e) {
            throw new DuplicateEntityException(COUNTRY_NAME_EXISTS, newCountry.getName());
        }
    }

    @Override
    public void deleteCountry(String name) {
        try {
            countryRepository.deleteCountry(name);
        } catch (Exception e) {
            throw new EntityNotFoundException (COUNTRY_NAME_NOT_FOUND, name);
        }
    }
}
