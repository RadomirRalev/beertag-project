package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Style;
import com.beertag.demo.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Service
public class StyleServiceImpl implements StyleService {
    private StyleRepository styleRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
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
    public Style getSpecificStyle(String name) {
        try {
            return styleRepository.getSpecificStyle(name);
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
            styleRepository.createStyle(newStyle);
            return newStyle;
        } catch (Exception e) {
            throw new DuplicateEntityException(STYLE_NAME_EXISTS, newStyle.getName());
        }
    }

    @Override
    public void deleteStyle(String name) {
        try {
            styleRepository.deleteStyle(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(STYLE_NAME_NOT_FOUND, name);
        }
    }
}
