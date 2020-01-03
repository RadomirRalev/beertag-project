//package com.beertag.demo.controllers.restcontrollers;
//
//import com.beertag.demo.exceptions.DuplicateEntityException;
//import com.beertag.demo.exceptions.EntityNotFoundException;
//import com.beertag.demo.exceptions.InvalidPermission;
//import com.beertag.demo.helpers.BeerCollectionHelper;
//import com.beertag.demo.models.beer.BeerDto;
//import com.beertag.demo.models.beer.Beer;
//import com.beertag.demo.models.DtoMapper;
//import com.beertag.demo.models.user.User;
//import com.beertag.demo.services.BeerService;
//import com.beertag.demo.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/beers")
//public class BeerController {
//    private BeerService service;
//    private UserService userService;
//    private DtoMapper mapper;
//
//    @Autowired
//    public BeerController(BeerService service, DtoMapper mapper, UserService userService) {
//        this.service = service;
//        this.mapper = mapper;
//        this.userService = userService;
//    }
//
//    @GetMapping("/{id}")
//    public Beer getById(@PathVariable int id) {
//        try {
//            return service.getById(id);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }
//
//    @GetMapping
//    public List<Beer> getBeersList(@RequestParam(required = false) String name,
//                                   @RequestParam(required = false) String style,
//                                   @RequestParam(required = false) String tag,
//                                   @RequestParam(required = false) String sortType) {
//        List<Beer> result = null;
//        if (name != null && !name.isEmpty()) {
//            result = service.getBeerByName(name);
//        }
//        if (style != null && !style.isEmpty()) {
//            result = service.getBeersByStyleName(style);
//        }
//        if (tag != null && !tag.isEmpty()) {
//            result = service.getBeersByTagName(tag);
//        }
//        if ((name == null || name.isEmpty()) && (style == null || style.isEmpty())
//                && (tag == null || tag.isEmpty())) {
//            result = service.getBeersList();
//        }
//        return BeerCollectionHelper.sortBeersList(result, sortType);
//    }
//
//    @GetMapping("/search")
//    @ResponseBody
//    public List<Beer> getBeerByName(@RequestParam(defaultValue = "test") String name) {
//        try {
//            return service.getBeerByName(name);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }
//
//    @PutMapping("/{id}")
//    public Beer update(@PathVariable int id, @RequestBody @Valid BeerDto beerDto,
//                       @RequestHeader(name = "Authorization") String authorization) {
//        User requestUser = userService.getByUsername(authorization);
//        try {
//            Beer beerToBeUpdated = mapper.fromDto(beerDto);
//            return service.update(id, beerToBeUpdated, requestUser);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        } catch (InvalidPermission e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
//        }
//    }
//
//    @PostMapping
//    public Beer createBeer(@RequestBody @Valid BeerDto newBeerDto,
//                           @RequestHeader(name = "Authorization") String authorization) {
//        try {
//            User requestUser = userService.getByUsername(authorization);
//            Beer newBeer = mapper.fromDto(newBeerDto);
//            newBeer.setCreatedBy(requestUser);
//            return service.createBeer(newBeer);
//        } catch (DuplicateEntityException e) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
//        }
//    }
//
//    @DeleteMapping("{id}")
//    public void deleteBeer(@PathVariable int id) {
//        try {
//            service.deleteBeer(id);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }
//}