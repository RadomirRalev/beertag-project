package com.beertag.demo.services;

import com.beertag.demo.models.beer.Country;

import java.util.List;

public interface CountryService {
    Country getCountryById(int id);

    List<Country> getCountriesList();

    Country getSpecificCountry(String name);

    Country update(int id, Country country);

    Country createCountry(Country newCountry);

    void deleteCountry(String name);
}
