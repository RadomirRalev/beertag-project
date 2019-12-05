package com.beertag.demo.controllers;

import com.beertag.demo.models.Style;
import com.beertag.demo.services.StylesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/style")
public class StyleController {
    private StylesService service;

    public StyleController(StylesService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Style getetById(@PathVariable int id) {
        return service.getStyleById(id);
    }

    @GetMapping
    public List<Style> getStylesList() {
        List result = service.getStylesList();
        return result;
    }

    @GetMapping("/search")
    @ResponseBody
    public Style getSpecificStyle(@RequestParam(defaultValue = "test") String name) {
        return service.getSpecificStyle(name);
    }

    @PutMapping("/{id}")
    public Style update(@PathVariable int id, @RequestBody Style style) {
        return service.update(id, style);
    }

    @PostMapping
    public void createStyle(@RequestBody Style newStyle) {
        service.createStyle(newStyle);
    }

    @DeleteMapping("{name}")
    public void deleteStyle(@PathVariable String name) {
        service.deleteStyle(name);
    }
}