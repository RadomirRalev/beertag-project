package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.exceptions.InvalidPermission;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Tag;
import com.beertag.demo.models.user.User;
import com.beertag.demo.repositories.BeerRepository;
import com.beertag.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.exceptions.Constants.*;

@Service
public class BeerServiceImpl implements BeerService {
    private BeerRepository repository;
    private TagRepository tagRepository;


    @Autowired
    public BeerServiceImpl(BeerRepository repository, TagRepository tagRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Beer getById(int id) {
        try {
            return repository.getById(id);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(BEER_ID_NOT_FOUND, id);
        }
    }

    @Override
    public List<Beer> getBeersList() {
//        try {
            return repository.getBeerList();
//        } catch (Exception e) {
//            throw new EntityNotFoundException(LIST_EMPTY);
//        }
    }

    @Override
    public List<Beer> getBeerByName(String name) {
        try {
            return repository.getBeerByName(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(BEER_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public List<Beer> getBeersByStyleName(String styleName) {
        try {
            return repository.getBeersByStyleName(styleName);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(STYLE_NAME_NOT_FOUND, styleName);
        }
    }

    @Override
    public List<Beer> getBeersByTagName(String tagName) {
        List<Beer> listB = new ArrayList<>();
        List<Tag> tag = tagRepository.getTagByName(tagName);
        for (int i = 0; i < tag.size(); i++) {
            listB.addAll(tag.get(i).getBeers());
        }
        return listB;
    }

    @Override
    public Beer createBeer(Beer newBeer) {
        try {
            return repository.createBeer(newBeer);
        } catch (Exception ex) {
            throw new DuplicateEntityException(BEER_NAME_EXISTS, newBeer.getName());
        }
    }

    @Override
    public void deleteBeer(int id) {
            repository.deleteBeer(id);
    }

    @Override
    public Beer update(int id, Beer beerToBeUpdated, User requestUser) {
        if (beerToBeUpdated.getCreateBy().getId() != requestUser.getId() &&
                requestUser.getRoles().stream().noneMatch(role -> role.getName().equals("admin"))){
            throw new InvalidPermission(USER_CAN_NOT_MODIFY,requestUser.getUsername(),beerToBeUpdated.getName());
        }
            return repository.update(id, beerToBeUpdated);
    }
}