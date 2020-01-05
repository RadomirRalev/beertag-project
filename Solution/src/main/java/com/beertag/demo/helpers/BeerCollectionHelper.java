package com.beertag.demo.helpers;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.services.BeerService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeerCollectionHelper {

    public static List<Beer> filterBeersList(String style, String tag, String brewery, String country, String alcohol, BeerService service) {
        List<Beer> beersList = service.getBeersList();
        if (tag != null && !tag.isEmpty()) {
            beersList = service.getBeersByTagName(tag);
        }
        if (style != null && !style.isEmpty()) {
            beersList = beersList.stream()
                    .filter((b -> b.getStyle().getName().equals(style)))
                    .collect(Collectors.toList());
        }
        if (brewery != null && !brewery.isEmpty()) {
            beersList = beersList.stream()
                    .filter((b -> b.getBrewery().getName().equals(brewery)))
                    .collect(Collectors.toList());
        }
        if (country != null && !country.isEmpty()) {
            beersList = beersList.stream()
                    .filter((b -> b.getOriginCountry().getName().equals(country)))
                    .collect(Collectors.toList());
        }
        beersList = getBeersByAlcohol(alcohol, beersList);
        return beersList;
    }

    public static List<Beer> sortBeersList(List<Beer> beerList, String sortType) {
        if (sortType != null && !sortType.isEmpty()) {
            beerList = sortBeers(beerList, sortType);
        }
        return beerList;
    }

    private static List<Beer> getBeersByAlcohol(String alcohol, List<Beer> beersList) {
        if (alcohol != null && !alcohol.isEmpty()) {
            if (alcohol.equalsIgnoreCase("Low")) {
                beersList = beersList.stream()
                        .filter((b -> b.getAbvTag() <= 5))
                        .collect(Collectors.toList());
            }
            if (alcohol.equalsIgnoreCase("Medium")) {
                beersList = beersList.stream()
                        .filter((b -> b.getAbvTag() > 5 && b.getAbvTag() < 10))
                        .collect(Collectors.toList());
            }
            if (alcohol.equalsIgnoreCase("High")) {
                beersList = beersList.stream()
                        .filter((b -> b.getAbvTag() >= 10))
                        .collect(Collectors.toList());
            }
        }
        return beersList;
    }

    private static List<Beer> sortBeers(List<Beer> beerList, String sortType) {
        if (sortType.equalsIgnoreCase("name")) {
            beerList = beerList.stream()
                    .sorted(Comparator.comparing(sortByName()))
                    .collect(Collectors.toList());
        } else if (sortType.equalsIgnoreCase("abv")) {
            beerList = beerList.stream()
                    .sorted(Comparator.comparing(sortByABV()))
                    .collect(Collectors.toList());
        } else if (sortType.equalsIgnoreCase("rating")) {
            beerList = beerList.stream()
                    .sorted(Comparator.comparing(sortByRating()))
                    .collect(Collectors.toList());
        }
        return beerList;
    }

    private static Function<Beer, String> sortByName() {
            return Beer::getName;
    }

    private static Function<Beer, Double> sortByABV() {
        return Beer::getAbvTag;
    }

    private static Function<Beer, Double> sortByRating() {
        return Beer::getAvgRating;
    }
}