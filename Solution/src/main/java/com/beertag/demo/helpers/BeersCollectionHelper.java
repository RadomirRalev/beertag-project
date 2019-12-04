package com.beertag.demo.helpers;

import com.beertag.demo.models.Beers;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeersCollectionHelper {
    public static List<Beers> filterByName(List<Beers> beersList, String name) {
        if (name != null && !name.isEmpty()) {
            beersList = beersList.stream()
                    .filter(beers -> beers.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return beersList;
    }

    public static List<Beers> filterByStyle(List<Beers> beersList, String style) {
        if (style != null && !style.isEmpty()) {
            beersList = beersList.stream()
                    .filter(beers -> beers.getStyle().getName().toLowerCase().contains(style.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return beersList;
    }

    public static List sortBeersList(List<Beers> beersList, String sortType) {
        if (sortType != null && !sortType.isEmpty() &&
                (sortType.equalsIgnoreCase("name") || sortType.equalsIgnoreCase("abv"))) {
            beersList = beersList.stream()
                    .sorted(Comparator.comparing(getTypeOfSort(sortType)))
                    .collect(Collectors.toList());
        }
        return beersList;
    }

    private static Function<Beers, String> getTypeOfSort(String sortType) {
        if (sortType.equalsIgnoreCase("name")) {
            return Beers::getName;
        }
        return Beers::getAbvTag;
    }
}
