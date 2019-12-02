package com.beertag.demo.repositories;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class BeersRepositoryImpl implements BeersRepository {

    private List<Beers> beersList;

    public BeersRepositoryImpl() {
        beersList = new ArrayList<>();
        beersList.add(new Beers("Zagorka", "kkkk", "Bulgaria",
                "okok", "jsjsj", "2.14", "pop", "tag"));
        beersList.add(new Beers("Shumensko", "dgfdgd", "Bulgaria",
                "okgsdgok", "afgfdg", "3.12", "ads", "tag"));
        beersList.add(new Beers("Pirinsko", "dsfdf", "Serbia",
                "qwerrr", "hjgjk", "2.84", "arghhtr", "tag"));
    }

    @Override
    public List<Beers> getBeersList() {
        return beersList;
    }

    @Override
    public Beers getSpecificBeer(String name) {
        return getBeers(name);
    }

    @Override
    public List<Beers> filterBeers(String filterType, String filterQuery) {
        return beersList.stream()
                .filter(beers -> beers.isEqualToFilterParametre(filterType, filterQuery))
                .collect(Collectors.toList());
    }

    @Override
    public void createBeer(Beers newBeer) {
        beersList.add(newBeer);
    }

    @Override
    public void deleteBeer(String name) {
        Beers beerToBeRemoved = getBeers(name);
        beersList.remove(beerToBeRemoved);
    }

    @Override
    public List<Beers> sortEntries(String sortType) {
        return beersList.stream()
                .sorted(Comparator.comparing(getTypeOfSort(sortType)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkBeerExists(String name) {
        return beersList.stream()
                .anyMatch(beer -> beer.getName().equals(name));
    }

    private Beers getBeers(@PathVariable String name) {
        return beersList.stream()
                .filter(beers -> beers.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Beer %s not found in the database", name)));
    }

    private Function<Beers, String> getTypeOfSort(String sortType) {
        if (sortType.equalsIgnoreCase("name")) {
            return Beers::getName;
        }
        return Beers::getAbvTag;
    }
}
