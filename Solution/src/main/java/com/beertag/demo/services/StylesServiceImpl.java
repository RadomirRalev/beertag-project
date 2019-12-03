package com.beertag.demo.services;

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
}
