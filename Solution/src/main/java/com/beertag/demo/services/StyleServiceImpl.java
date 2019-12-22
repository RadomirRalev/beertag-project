package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Style;
import com.beertag.demo.repositories.BeerRepository;
import com.beertag.demo.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Service
public class StyleServiceImpl implements StyleService {
    private StyleRepository styleRepository;
    private BeerRepository beerRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository, BeerRepository beersRepository) {
        this.styleRepository = styleRepository;
        this.beerRepository = beersRepository;
    }

    @Override
    public Style getStyleById(int id) {
        try {
            return styleRepository.getStyleById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(STYLE_ID_NOT_FOUND, id);
        }
    }

    @Override
    public List<Style> getStylesList() {
        try {
            return styleRepository.getStylesList();
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public List<Style> getStyleByName(String name) {
        try {
            return styleRepository.getStyleByName(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(STYLE_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Style update(int id, Style style) {
        try {
            styleRepository.update(id, style);
            return style;
        } catch (Exception e) {
            throw new EntityNotFoundException(STYLE_ID_NOT_FOUND, id);
        }
    }

    @Override
    public Style createStyle(Style newStyle) {
        try {
            return styleRepository.createStyle(newStyle);
        } catch (Exception e) {
            throw new DuplicateEntityException(STYLE_NAME_EXISTS, newStyle.getName());
        }
    }

    @Override
    public List<Beer> getBeersByStyleId(int styleId) {
        return beerRepository.getBeersByStyleId(styleId);
    }

    @Override
    public void deleteStyle(int id) {
            styleRepository.deleteStyle(id);
    }
}
