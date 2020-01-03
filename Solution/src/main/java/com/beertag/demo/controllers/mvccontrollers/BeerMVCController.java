package com.beertag.demo.controllers.mvccontrollers;

import com.beertag.demo.models.DtoMapper;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.BeerDto;
import com.beertag.demo.services.BeerService;
import com.beertag.demo.services.StyleService;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BeerMVCController {
    private BeerService service;
    private UserService userService;
    private DtoMapper mapper;
    private StyleService styleService;

    @Autowired
    public BeerMVCController(BeerService service, DtoMapper mapper, UserService userService, StyleService styleService) {
        this.service = service;
        this.mapper = mapper;
        this.userService = userService;
        this.styleService = styleService;
    }

    @GetMapping("/beers")
    public String showBeers(Model model) {
        model.addAttribute("beers", service.getBeersList());
        return "beers";
    }

    @GetMapping("beers/new")
    public String showNewBeerForm(Model model) {
        model.addAttribute("beer", new BeerDto());
        model.addAttribute("styles", styleService.getStylesList());
        return "beer";
    }

    @PostMapping("beers/new")
    public String createBeer(@Valid @ModelAttribute("beer") BeerDto beer, BindingResult errors) {
        if (errors.hasErrors()) {
            return "beer";
        }
        Beer toCreate = new Beer();
        toCreate.setName(beer.getName());
        toCreate.setAbvTag(beer.getAbvTag());
        toCreate.setStyle(styleService.getStyleById(beer.getStyleId()));
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

    @GetMapping("allbeers/{beerId}")
    public String getBeerById(@PathVariable int beerId, Model model) {
        Beer beer = service.getById(beerId);
        model.addAttribute("beer", beer);
        return "details";
    }
}
