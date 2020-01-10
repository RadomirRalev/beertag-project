package com.beertag.demo.services;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Rating;
import com.beertag.demo.models.beer.Tag;
import com.beertag.demo.repositories.BeerRepository;
import com.beertag.demo.repositories.RatingRepository;
import com.beertag.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.beertag.demo.exceptions.Constants.*;

@Service
public class BeerServiceImpl implements BeerService {
    private BeerRepository repository;
    private TagRepository tagRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public BeerServiceImpl(BeerRepository repository, TagRepository tagRepository,
                           RatingRepository ratingRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
        this.ratingRepository = ratingRepository;

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
            return repository.getBeerList();
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
    public List<Beer> getBeersByBreweryName(String breweryName) {
        try {
            return repository.getBeersByBreweryName(breweryName);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(BREWERY_NAME_NOT_FOUND, breweryName);
        }
    }

    @Override
    public List<Beer> getBeersByOriginCountry(String originCountry) {
        try {
            return repository.getBeersByOriginCountry(originCountry);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(COUNTRY_NAME_NOT_FOUND, originCountry);
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
        newBeer.setStatus(ENABLED);
            return repository.createBeer(newBeer);
    }

    @Override
    public void deleteBeer(int id) {
            repository.deleteBeer(id);
    }

    @Override
    public Beer update(int id, Beer beerToBeUpdated) {
//        if (beerToBeUpdated.getCreatedBy().getId() != requestUserDetail.getId() &&
//                requestUserDetail.getRoles().stream().noneMatch(role -> role.getRole().equals("admin"))){
//            throw new InvalidPermission(USER_CAN_NOT_MODIFY, requestUserDetail.getUsername(),beerToBeUpdated.getName());
//        }

            return repository.update(id, beerToBeUpdated);
    }

    @Override
    public void updateAvgRatingOfBeer(int beerId) {
        List<Rating> ratingList = ratingRepository.getRatingsOfSpecificBeer(beerId);
        double avgRating = 0;
        for (Rating rating : ratingList) {
            avgRating += rating.getRating();
        }
        avgRating = avgRating/ratingList.size();
        repository.updateAvgRating(beerId, avgRating);
    }

    @Override
    public List<Tag> getTags(int id) {
        return repository.getBeerTags(id);
    }

    public Page<Beer> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Beer> list = getBeersList();
        if (list.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            list = list.subList(startItem, toIndex);
        }
        Page<Beer> beerPage
                = new PageImpl<Beer>(list, PageRequest.of(currentPage, pageSize), list.size());
        return beerPage;
    }
}