package com.beertag.demo.models;

import com.beertag.demo.services.CountryService;
import com.beertag.demo.services.StylesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    private StylesService stylesService;
    private CountryService countryService;

    @Autowired
    public DtoMapper(StylesService stylesService, CountryService countryService) {
        this.stylesService = stylesService;
        this.countryService = countryService;
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
}
