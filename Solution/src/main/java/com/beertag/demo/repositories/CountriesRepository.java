package com.beertag.demo.repositories;

import com.beertag.demo.models.Country;

import java.util.List;

public interface CountriesRepository {

    Country getCountryById(int id);

    List<Country> getCountriesList();

    Country getSpecificCountry(String name);

    void update(int id, Country country);

    void createCountry(Country newCountry);

    boolean checkCountryExists(String name);

    void deleteCountry(String name);
}
