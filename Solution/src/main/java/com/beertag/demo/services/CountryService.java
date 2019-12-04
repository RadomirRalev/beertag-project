package com.beertag.demo.services;

import com.beertag.demo.models.Country;

import java.util.List;

public interface CountryService {
    Country getCountryById(int id);

    List<Country> getCountriesList();

    Country getSpecificCountry(String name);

    Country update(int id, Country country);

    void createCountry(Country newCountry);

    void deleteCountry(String name);
}
