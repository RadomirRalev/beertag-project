package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Country;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CountriesRepositoryImpl implements CountriesRepository {
    private static int countryID;
    private List<Country> countryList;

    public CountriesRepositoryImpl() {
        countryList = new ArrayList<>();
        Country country = new Country("Bulgaria");
        country.setId(CountriesRepositoryImpl.countryID++);
        countryList.add(country);
        country = new Country("Serbia");
        country.setId(CountriesRepositoryImpl.countryID++);
        countryList.add(country);
        country = new Country("Czech Republic");
        country.setId(CountriesRepositoryImpl.countryID++);
        countryList.add(country);
    }

    @Override
    public Country getCountryById(int id) {
        return countryList.stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Country", id));
    }

    @Override
    public List<Country> getCountriesList() {
        return countryList;
    }

    @Override
    public Country getSpecificCountry(String name) {
        return getCountry(name);
    }

    @Override
    public void update(int id, Country country) {
        for (int i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).getId() == id) {
                countryList.set(i, country);
                break;
            }
        }
    }

    private Country getCountry(@PathVariable String name) {
        return countryList.stream()
                .filter(beers -> beers.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Country %s not found in the database", name)));
    }

    @Override
    public void createCountry(Country newCountry) {
        countryList.add(newCountry);
    }

    @Override
    public boolean checkCountryExists(String name) {
        return countryList.stream()
                .anyMatch(country -> country.getName().equals(name));
    }

    @Override
    public void deleteCountry(String name) {
        Country countryToBeRemoved = getCountry(name);
        countryList.remove(countryToBeRemoved);
    }
}