package com.beertag.demo.controllers.mvccontrollers.User;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

import static com.beertag.demo.helpers.UserHelper.currentPrincipalName;


@Controller
public class ProfileController {

    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping ("/wishlist")
    public String wishlist(Model model){
        Set<Beer> beers = userService.getWishList(currentPrincipalName());

        model.addAttribute("beers", beers);
        return "/users/wishlist";
    }

    @GetMapping ("/dranklist")
    public String dranklist(Model model){
        Set<Beer> beers = userService.getDrankList(currentPrincipalName());

        model.addAttribute("beers", beers);
        return "/users/dranklist";
    }
}
