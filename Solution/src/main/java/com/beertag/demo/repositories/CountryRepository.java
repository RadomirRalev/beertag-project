package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Country;

import java.util.List;

public interface CountryRepository {

    Country getCountryById(int id);

    List<Country> getCountriesList();

    List<Country> getCountryByName(String name);

    Country update(int id, Country country);

    Country createCountry(Country newCountry);

    boolean checkCountryExists(String name);

    void deleteCountry(int id);
}
