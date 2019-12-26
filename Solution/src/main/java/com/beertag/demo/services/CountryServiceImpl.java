package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Country;
import com.beertag.demo.repositories.BeerRepository;
import com.beertag.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.exceptions.Constants.*;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;
    private BeerRepository beerRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, BeerRepository beerRepository) {
        this.countryRepository = countryRepository;
        this.beerRepository = beerRepository;
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
    public List<Country> getCountryByName(String name) {
        try {
            return countryRepository.getCountryByName(name);
        } catch (Exception ex) {
            throw new EntityNotFoundException (COUNTRY_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public List<Beer> getBeersByCountryId(int countryId) {
        return beerRepository.getBeersByCountryId(countryId);
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
           return countryRepository.createCountry(newCountry);
        } catch (Exception e) {
            throw new DuplicateEntityException(COUNTRY_NAME_EXISTS, newCountry.getName());
        }
    }

    @Override
    public void deleteCountry(int id) {
            countryRepository.deleteCountry(id);
    }
}
