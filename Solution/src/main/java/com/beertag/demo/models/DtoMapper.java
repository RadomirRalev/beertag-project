package com.beertag.demo.models;

import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.services.BreweryService;
import com.beertag.demo.services.CountryService;
import com.beertag.demo.services.StylesService;
import com.beertag.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    private static final String NOT_ADULT = "User is under 18 years";
    private StylesService stylesService;
    private CountryService countryService;
    private UserServices userServices;
    private BreweryService breweryService;

    @Autowired
    public DtoMapper(StylesService stylesService, CountryService countryService, UserServices userServices, BreweryService breweryService) {
        this.stylesService = stylesService;
        this.countryService = countryService;
        this.userServices = userServices;
        this.breweryService = breweryService;
    }

    public Beers fromDto(BeerDto beerDto) {
        Beers beer = new Beers(beerDto.getName(), beerDto.getDescription(), beerDto.getAbvTag(), beerDto.getPicture(), beerDto.getTag());
        Style style = stylesService.getStyleById(beerDto.getStyleId());
        Country country = countryService.getCountryById(beerDto.getOriginCountryId());
        Brewery brewery = breweryService.getBreweryById(beerDto.getBreweryId());
        beer.setStyle(style);
        beer.setOriginCountry(country);
        beer.setBrewery(brewery);
        return beer;
    }

    public User fromDto(UserDto userDto) {
        if (userServices.isUserAdult(userDto.getDay(), userDto.getMonth(), userDto.getBirthYear())) {
            return new User(userDto.getFirstName(), userDto.getLastName(), userDto.getUserName(), userDto.getEmail());
        }
        throw new InvalidAgeException(NOT_ADULT);
    }

}
