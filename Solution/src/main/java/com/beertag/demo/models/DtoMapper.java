package com.beertag.demo.models;

import com.beertag.demo.services.StylesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    private StylesService stylesService;

    @Autowired
    public DtoMapper(StylesService stylesService) {
        this.stylesService = stylesService;
    }

    public Beers fromDto(BeerDto beerDto) {
        Beers beer = new Beers(beerDto.getName(), beerDto.getDescription(),
                beerDto.getOriginCountry(), beerDto.getBrewery(),
                beerDto.getAbvTag(), beerDto.getPicture(), beerDto.getTag());
        Style style = stylesService.getStyleById(beerDto.getStyleId());
        beer.setStyle(style);
        return beer;
    }
}
