package com.beertag.demo.controllers.mvccontrollers;

import com.beertag.demo.models.DtoMapper;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.services.BeerService;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BeerMVCController {
    private BeerService service;
    private UserService userService;
    private DtoMapper mapper;

    @Autowired
    public BeerMVCController(BeerService service, DtoMapper mapper, UserService userService) {
        this.service = service;
        this.mapper = mapper;
        this.userService = userService;
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
