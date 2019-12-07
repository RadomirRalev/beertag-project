package com.beertag.demo.controllers;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Tag;
import com.beertag.demo.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public Tag getByID(@PathVariable int id) {
        return tagService.getTagById(id);
    }

    @GetMapping
    public List<Tag> getTagList() {
        return tagService.getTagList();
    }

    @GetMapping("/search")
    @ResponseBody //какво прави ?
    public Tag getSpecificTag (@RequestParam(defaultValue = "test") String name){
        try {
            return tagService.getSpecificTag(name);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Tag create(@RequestBody @Valid Tag tag) {
        try {
            tagService.createTag(tag);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return tag;
    }

    @PutMapping("/{id}")
    public  Tag update (@RequestBody @Valid Tag tag, @PathVariable int id){
        try {
            tagService.update(id, tag);
        }
        catch (DuplicateEntityException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return tag;
    }

    @DeleteMapping("{name}")
    public void delete (@PathVariable String name){
        try {
            tagService.deleteTag(name);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
