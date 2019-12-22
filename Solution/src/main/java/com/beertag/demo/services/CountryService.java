package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Country;

import java.util.List;

public interface CountryService {
    Country getCountryById(int id);

    List<Country> getCountriesList();

    List<Country> getCountryByName(String name);

    List<Beer> getBeersByCountryId(int countryId);

    Country update(int id, Country country);

    Country createCountry(Country newCountry);

    void deleteCountry(int id);
}
