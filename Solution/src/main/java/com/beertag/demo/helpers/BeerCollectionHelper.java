package com.beertag.demo.helpers;

import com.beertag.demo.models.Beer;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeerCollectionHelper {
    public static List<Beer> filterByName(List<Beer> beerList, String name) {
        if (name != null && !name.isEmpty()) {
            beerList = beerList.stream()
                    .filter(beers -> beers.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return beerList;
    }

    public static List<Beer> filterByStyle(List<Beer> beerList, String style) {
        if (style != null && !style.isEmpty()) {
            beerList = beerList.stream()
                    .filter(beers -> beers.getStyle().getName().toLowerCase().contains(style.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return beerList;
    }

    public static List<Beer> sortBeersList(List<Beer> beerList, String sortType) {
        if (sortType != null && !sortType.isEmpty() &&
                (sortType.equalsIgnoreCase("name") || sortType.equalsIgnoreCase("abv"))) {
            beerList = beerList.stream()
                    .sorted(Comparator.comparing(getTypeOfSort(sortType)))
                    .collect(Collectors.toList());
        }
        return beerList;
    }

    private static Function<Beer, String> getTypeOfSort(String sortType) {
        if (sortType.equalsIgnoreCase("name")) {
            return Beer::getName;
        }
        return Beer::getAbvTag;
    }
}
