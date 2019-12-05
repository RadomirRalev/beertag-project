package com.beertag.demo.models;

import com.beertag.demo.services.BreweryService;
import com.beertag.demo.services.CountryService;
import com.beertag.demo.services.StylesService;
import com.beertag.demo.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private StylesService stylesService;
    private CountryService countryService;
    private BreweryService breweryService;
    private TagService tagService;

    @Autowired
    public DtoMapper(StylesService stylesService, CountryService countryService,
                     BreweryService breweryService, TagService tagService) {
        this.stylesService = stylesService;
        this.countryService = countryService;
        this.breweryService = breweryService;
        this.tagService = tagService;
    }

    public Beers fromDto(BeerDto beerDto) {
        Beers beer = new Beers(beerDto.getName(), beerDto.getDescription(), beerDto.getAbvTag(), beerDto.getPicture());
        Style style = stylesService.getStyleById(beerDto.getStyleId());
        Country country = countryService.getCountryById(beerDto.getOriginCountryId());
        Brewery brewery = breweryService.getBreweryById(beerDto.getBreweryId());
        Tag tag = tagService.getTagById(beerDto.getTagId());
        beer.setStyle(style);
        beer.setOriginCountry(country);
        beer.setBrewery(brewery);
        beer.setTag(tag);
        return beer;
    }
}
