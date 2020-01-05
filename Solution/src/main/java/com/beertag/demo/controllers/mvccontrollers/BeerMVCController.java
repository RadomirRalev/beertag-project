package com.beertag.demo.controllers.mvccontrollers;

import com.beertag.demo.helpers.BeerCollectionHelper;
import com.beertag.demo.models.DtoMapper;
import com.beertag.demo.models.beer.*;
import com.beertag.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BeerMVCController {
    private BeerService service;
    private UserService userService;
    private DtoMapper mapper;
    private StyleService styleService;
    private BreweryService breweryService;
    private TagService tagService;
    private CountryService countryService;

    @Autowired
    public BeerMVCController(BeerService service, DtoMapper mapper, UserService userService,
                             StyleService styleService, BreweryService breweryService, TagService tagService,
                             CountryService countryService) {
        this.service = service;
        this.mapper = mapper;
        this.userService = userService;
        this.styleService = styleService;
        this.breweryService = breweryService;
        this.tagService = tagService;
        this.countryService = countryService;
    }

    @GetMapping("/beers")
    public String showBeers(Model model) {
        model.addAttribute("beers", service.getBeersList());
        return "beers";
    }

    @GetMapping("beers/new")
    public String showNewBeerForm(Model model) {
        model.addAttribute("newbeer", new BeerDto());
        model.addAttribute("countries", countryService.getCountriesList());
        model.addAttribute("styles", styleService.getStylesList());
        model.addAttribute("breweries", breweryService.getBreweriesList());
        model.addAttribute("tags", tagService.getTagList());
        return "newbeer";
    }

    @PostMapping("beers/new")
    public String createBeer(@Valid @ModelAttribute("beer") BeerDto beer, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "beer";
        }
        Beer toCreate = new Beer();
        toCreate.setName(beer.getName());
        toCreate.setAbvTag(beer.getAbvTag());
        toCreate.setOriginCountry(countryService.getCountryById(beer.getOriginCountryId()));
        toCreate.setStyle(styleService.getStyleById(beer.getStyleId()));
        toCreate.setBrewery(breweryService.getBreweryById(beer.getBreweryId()));
        service.createBeer(toCreate);
        return "redirect:/beers";
    }

    @GetMapping("/allbeers")
    public String getBeersList(Model model) {
        List<Beer> allBeers = service.getBeersList();
        model.addAttribute("message", "Hello World!");
        model.addAttribute("beers", allBeers);
        return "allbeers";
    }

    @GetMapping("/filterbyname")
    public String filterByName(@RequestParam(required = false) String name, Model model) {
        List<Beer> filteredBeers = service.getBeerByName(name);
        model.addAttribute("beers", filteredBeers);
        return "beers";
    }

    @GetMapping("/filterbystyle")
    public String filterByStyle(@RequestParam(required = false) String style, Model model) {
        List<Beer> filteredBeers = service.getBeersByStyleName(style);
        model.addAttribute("beers", filteredBeers);
        return "beers";
    }

    @GetMapping("/filterbytag")
    public String filterByTag(@RequestParam(required = false) String tag, Model model) {
        List<Beer> filteredBeers = service.getBeersByTagName(tag);
        model.addAttribute("beers", filteredBeers);
        return "beers";
    }

//    @GetMapping("allbeers/{beerId}")
//    public String getBeerById(@PathVariable int beerId, Model model) {
//        Beer beer = service.getById(beerId);
//        model.addAttribute("beer", beer);
//        return "details";
//    }

    @GetMapping("/beers/advancedsearch")
    public String filterBeers(Model model){
        List<Style> stylesList = styleService.getStylesList();
        List<Tag> tagsList = tagService.getTagList();
        List<Country> countriesList = countryService.getCountriesList();
        List<Brewery> breweriesList = breweryService.getBreweriesList();
        model.addAttribute("styles", stylesList);
        model.addAttribute("tags", tagsList);
        model.addAttribute("countries", countriesList);
        model.addAttribute("breweries", breweriesList);
        model.addAttribute("parametres", new Parametres());
        return "detailedsearch";
    }

    @GetMapping("/beers/filter")
    public String getFilterBeers(@ModelAttribute Parametres parametres,
                                 @RequestParam(required = false) String style,
                                 @RequestParam(required = false) String tag,
                                 @RequestParam(required = false) String brewery,
                                 @RequestParam(required = false) String country,
                                 @RequestParam(required = false) String alcohol,
                                 @RequestParam(required = false) String sortType,
                                 Model model) {
        style = parametres.getStyleSearch();
        tag = parametres.getTagSearch();
        brewery = parametres.getBrewerySearch();
        country = parametres.getCountrySearch();
        List<Beer> result = BeerCollectionHelper.filterBeersList(style, tag, brewery, country, alcohol, service);
        result = BeerCollectionHelper.sortBeersList(result, sortType);
        model.addAttribute("beers", result);
        return "beers";
    }
}
