package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Style;
import com.beertag.demo.services.StyleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/style")
public class StyleController {
    private StyleService service;

    public StyleController(StyleService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Style getetById(@PathVariable int id) {
        try {
            return service.getStyleById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public List<Style> getStylesList() {
        return service.getStylesList();
    }

    @GetMapping("/search")
    @ResponseBody
    public Style getSpecificStyle(@RequestParam String name) {
        try {
            return service.getSpecificStyle(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Style update(@PathVariable int id, @RequestBody Style style) {
        try {
            return service.update(id, style);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void createStyle(@RequestBody Style newStyle) {
        try {
            service.createStyle(newStyle);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("{name}")
    public void deleteStyle(@PathVariable String name) {
        try {
            service.deleteStyle(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}