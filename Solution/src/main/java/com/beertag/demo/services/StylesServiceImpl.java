package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Style;
import com.beertag.demo.repositories.StylesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StylesServiceImpl implements StylesService {
    private StylesRepository stylesRepository;

    @Autowired
    public StylesServiceImpl(StylesRepository stylesRepository) {
        this.stylesRepository = stylesRepository;
    }
    @Override
    public Style getStyleById(int id) {
        return stylesRepository.getStyleById(id);
    }

    @Override
    public List<Style> getStylesList() {
        return stylesRepository.getStylesList();
    }

    @Override
    public Style getSpecificStyle(String name) {
        try {
            return stylesRepository.getSpecificStyle(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException (String.format("Style %s not found in the database", name));
        }
    }

    @Override
    public Style update(int id, Style style) {
        stylesRepository.update(id, style);
        return style;
    }

    @Override
    public void createStyle(Style newStyle) {
        if (stylesRepository.checkStyleExists(newStyle.getName())) {
            throw new DuplicateEntityException("Style", newStyle.getName());
        }
        stylesRepository.createStyle(newStyle);
    }

    @Override
    public void deleteStyle(String name) {
        if (stylesRepository.checkStyleExists(name)) {
            throw new EntityNotFoundException(String.format("Style %s does not exist.", name));
        }
        stylesRepository.deleteStyle(name);
    }
}
