package com.beertag.demo.models;

import com.beertag.demo.exceptions.InvalidAgeException;
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

    @Autowired
    public DtoMapper(StylesService stylesService, CountryService countryService, UserServices userServices) {
        this.stylesService = stylesService;
        this.countryService = countryService;
        this.userServices = userServices;
    }

    public Beers fromDto(BeerDto beerDto) {
        Beers beer = new Beers(beerDto.getName(), beerDto.getDescription(), beerDto.getBrewery(),
                beerDto.getAbvTag(), beerDto.getPicture(), beerDto.getTag());
        Style style = stylesService.getStyleById(beerDto.getStyleId());
        Country country = countryService.getCountryById(beerDto.getOriginCountryId());
        beer.setStyle(style);
        beer.setOriginCountry(country);
        return beer;
    }
    public User fromDto(UserDto userDto) {
        if (userServices.isUserAdult(userDto.getDay(), userDto.getMonth(), userDto.getBirthYear())) {
            return new User(userDto.getFirstName(), userDto.getLastName(), userDto.getUserName(), userDto.getEmail());
        }
        throw new InvalidAgeException(NOT_ADULT);
    }

}
