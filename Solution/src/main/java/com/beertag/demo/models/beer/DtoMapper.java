package com.beertag.demo.models.beer;

import com.beertag.demo.models.BeerDto;
import com.beertag.demo.services.BreweryService;
import com.beertag.demo.services.CountryService;
import com.beertag.demo.services.StyleService;
import com.beertag.demo.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private StyleService styleService;
    private CountryService countryService;
    private BreweryService breweryService;
    private TagService tagService;

    @Autowired
    public DtoMapper(StyleService styleService, CountryService countryService,
                     BreweryService breweryService, TagService tagService) {
        this.styleService = styleService;
        this.countryService = countryService;
        this.breweryService = breweryService;
        this.tagService = tagService;
    }

    public Beer fromDto(BeerDto beerDto) {
        Beer beer = new Beer(beerDto.getName(), beerDto.getDescription(), beerDto.getAbvTag(), beerDto.getPicture());
        Style style = styleService.getStyleById(beerDto.getStyleId());
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
