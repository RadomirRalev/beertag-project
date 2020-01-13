package com.beertag.demo.controllers.mvccontrollers;

import com.beertag.demo.helpers.BeerCollectionHelper;
import com.beertag.demo.models.DtoMapper;
import com.beertag.demo.models.beer.*;

import com.beertag.demo.models.user.User;
import com.beertag.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.beertag.demo.helpers.UserHelper.*;
import static com.beertag.demo.constants.SQLQueryConstants.*;

import java.io.IOException;
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
    private RatingService ratingService;

    @Autowired
    public BeerMVCController(BeerService service, DtoMapper mapper, UserService userService,
                             StyleService styleService, BreweryService breweryService, TagService tagService,
                             CountryService countryService, RatingService ratingService) {
        this.service = service;
        this.mapper = mapper;
        this.userService = userService;
        this.styleService = styleService;
        this.breweryService = breweryService;
        this.tagService = tagService;
        this.countryService = countryService;
        this.ratingService = ratingService;
    }

    @GetMapping("/beers")
    public String showBeers(Model model) {
        model.addAttribute("beers", service.getBeersList());
        return "beers";
    }

    @GetMapping("/beers/{id}")
    public String showSingleBeer(@PathVariable("id") int id, Model model) {

        if (!currentPrincipalName().equals("anonymousUser")) {
            User user = userService.getByUsername(currentPrincipalName());
            boolean isBeerExistOnWishList = userService.isUserHaveCurrentBeerOnWishList(currentPrincipalName(), id);
            boolean isBeerEnabledOnWishList = userService.isBeerEnabletOnWishList(currentPrincipalName(), id);
            boolean isBeerExistOnDrankList = userService.isUserHaveCurrentBeerOnDrankList(currentPrincipalName(), id);
            boolean isBeerEnableOnDrankList = userService.isBeerEnabletOnDrankList(currentPrincipalName(), id);
            boolean rated = ratingService.isRated(currentPrincipalName(), id);
            model.addAttribute("user", user);
            model.addAttribute("isBeerExistOnWishList", isBeerExistOnWishList);
            model.addAttribute("isBeerEnabledOnWishList", isBeerEnabledOnWishList);
            model.addAttribute("isBeerExistOnDrankList", isBeerExistOnDrankList);
            model.addAttribute("isBeerEnableOnDrankList", isBeerEnableOnDrankList);
            model.addAttribute("rated", rated);
        }
        Rating rating = new Rating();
        Beer beer = service.getById(id);
        List<Tag> tagsOfBeer = service.getTags(id);
        model.addAttribute("beers", service.getBeersList());
        model.addAttribute("beer", beer);
        model.addAttribute("tags", tagsOfBeer);
        model.addAttribute("rating", rating);
        return "singlebeer";
    }

    @PostMapping("beers/{id}/wish")
    public String addBeerToWishlist(@PathVariable("id") int id) {

        userService.addBeerToWishList(currentPrincipalName(), id);
        return "redirect:";
    }

    @PostMapping("beers/{id}/wish-delete")
    public String deleteBeerToWishlist(@PathVariable("id") int beerId) {

        userService.setStatusWishList(currentPrincipalName(), beerId, DISABLE);
        return "redirect:";
    }

    @PostMapping("beers/{id}/wish-enable")
    public String enableBeerToWishlist(@PathVariable("id") int beerId) {

        userService.setStatusWishList(currentPrincipalName(), beerId, ENABLE);
        return "redirect:";
    }

    @PostMapping("beers/{id}/drank")
    public String addBeerToDranklist(@PathVariable("id") int id) {

        userService.addBeerToDrankList(currentPrincipalName(), id);

        return "redirect:";
    }

    @PostMapping("beers/{id}/drank-delete")
    public String deleteBeerToDranklist(@PathVariable("id") int id) {

        userService.setStatusDrankList(currentPrincipalName(), id, DISABLE);

        return "redirect:";
    }

    @PostMapping("beers/{id}/drank-enable")
    public String enableBeerToDranklist(@PathVariable("id") int id) {

        userService.setStatusDrankList(currentPrincipalName(), id, ENABLE);

        return "redirect:";
    }

    @PostMapping("beers/{id}/rating")
    public String rateBeer(@PathVariable("id") int id, @ModelAttribute("rating") Rating rating) {

        userService.rateBeer(currentPrincipalName(), id, rating.getRating());
        service.updateAvgRatingOfBeer(id);
        return "redirect:";
    }

    @GetMapping("beers/deletebeer/{id}")
    public String showDeleteBeerForm(@PathVariable("id") int id, Model model) {
        Beer beer = service.getById(id);
        model.addAttribute("beer", beer);
        service.deleteBeer(id);
        return "redirect:/beers";
    }


    @GetMapping("beers/updatebeer/{id}")
    public String showUpdateBeerForm(@PathVariable("id") int id, Model model) {
        Beer beer = service.getById(id);
        model.addAttribute("beer", beer);
        createParametres(model);
        return "updatebeer";
    }

    @PostMapping("beers/update")
    public String updateBeer(@ModelAttribute Parametres parametres,
                             Model model) {
        User requestUser = userService.getByUsername(currentPrincipalName());
        Beer beerToUpdate = service.getById(parametres.getBeerId());
        beerToUpdate.setName(parametres.getNameParam());
        beerToUpdate.setAbvTag(parametres.getAbvParam());
        beerToUpdate.setDescription(parametres.getDescriptionParam());
        beerToUpdate.setStyle(styleService.getStyleById(parametres.getStyleParamId()));
        beerToUpdate.setBrewery(breweryService.getBreweryById(parametres.getBreweryParamId()));
        beerToUpdate.setOriginCountry(countryService.getCountryById(parametres.getCountryParamId()));
        List<Tag> tagList = beerToUpdate.getTags();
        tagList.add(tagService.getTagById(parametres.getTagId()));
        service.update(beerToUpdate.getId(), beerToUpdate, requestUser);
        return "redirect:/beers";
    }

    @GetMapping("beers/new")
    public String showNewBeerForm(Model model) {
        createParametres(model);
        return "newbeer";
    }

    @PostMapping("beers/create")
    public String createBeer(@RequestParam("file") MultipartFile file,
                             @ModelAttribute Parametres parametres, Model model) throws IOException {
        model.addAttribute("file", file);
        Beer newBeer = new Beer();
        User user = userService.getByUsername(currentPrincipalName());
        newBeer.setCreatedBy(user);
        newBeer.setName(parametres.getNameParam());
        newBeer.setAbvTag(parametres.getAbvParam());
        newBeer.setDescription(parametres.getDescriptionParam());
        newBeer.setStyle(styleService.getStyleById(parametres.getStyleParamId()));
        newBeer.setBrewery(breweryService.getBreweryById(parametres.getBreweryParamId()));
        newBeer.setOriginCountry(countryService.getCountryById(parametres.getCountryParamId()));
        newBeer.setPicture(file.getBytes());
        service.createBeer(newBeer);
        return "success-beer";
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

    @GetMapping("/beers/advancedsearch")
    public String filterBeers(Model model) {
        createParametres(model);
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

    private void createParametres(Model model) {
        List<Style> stylesList = styleService.getStylesList();
        List<Tag> tagsList = tagService.getTagList();
        List<Country> countriesList = countryService.getCountriesList();
        List<Brewery> breweriesList = breweryService.getBreweriesList();
        model.addAttribute("styles", stylesList);
        model.addAttribute("tags", tagsList);
        model.addAttribute("countries", countriesList);
        model.addAttribute("breweries", breweriesList);
        model.addAttribute("parametres", new Parametres());
    }
}

