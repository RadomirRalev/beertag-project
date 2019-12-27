package com.beertag.demo.helpers;

import com.beertag.demo.models.beer.Beer;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeerCollectionHelper {

    public static List<Beer> sortBeersList(List<Beer> beerList, String sortType) {
        if (sortType != null && !sortType.isEmpty()) {
            beerList = sortBeers(beerList, sortType);
        }
        return beerList;
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
        return Beer::getAbvTag;
    }
}