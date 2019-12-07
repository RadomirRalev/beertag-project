package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Country;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Repository
public class CountryRepositoryImpl implements CountryRepository {
    private static int countryID;
    private List<Country> countryList;

    public CountryRepositoryImpl() {
        countryList = new ArrayList<>();
        Country country = new Country("Bulgaria");
        country.setId(CountryRepositoryImpl.countryID++);
        countryList.add(country);
        country = new Country("Serbia");
        country.setId(CountryRepositoryImpl.countryID++);
        countryList.add(country);
        country = new Country("Czech Republic");
        country.setId(CountryRepositoryImpl.countryID++);
        countryList.add(country);
    }

    @Override
    public Country getCountryById(int id) {
        return countryList.stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(COUNTRY_ID_NOT_FOUND, id));
    }

    @Override
    public List<Country> getCountriesList() {
        try {
            return countryList;
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public Country getSpecificCountry(String name) {
        try {
            return getCountry(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(COUNTRY_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Country update(int id, Country country) {
        for (int i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).getId() == id) {
                countryList.set(i, country);
                break;
            }
            if (i == countryList.size() - 1) {
                throw new EntityNotFoundException(COUNTRY_ID_NOT_FOUND, id);
            }
        }
        return country;
    }

    private Country getCountry(String name) {
        return countryList.stream()
                .filter(beers -> beers.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(COUNTRY_NAME_NOT_FOUND, name)));
    }

    @Override
    public Country createCountry(Country newCountry) {
        try {
            countryList.add(newCountry);
            return newCountry;
        } catch (Exception e) {
            throw new DuplicateEntityException(COUNTRY_NAME_EXISTS, newCountry.getName());
        }
    }

    @Override
    public boolean checkCountryExists(String name) {
        return countryList.stream()
                .anyMatch(country -> country.getName().equals(name));
    }

    @Override
    public void deleteCountry(String name) {
        try {
            Country countryToBeRemoved = getCountry(name);
            countryList.remove(countryToBeRemoved);
        } catch (Exception e) {
            throw new EntityNotFoundException(COUNTRY_NAME_NOT_FOUND, name);
        }
    }
}
