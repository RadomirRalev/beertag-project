package com.beertag.demo.services;

import com.beertag.demo.models.beer.Style;

import java.util.List;

public interface StyleService {
    Style getStyleById(int id);

    List<Style> getStylesList();

    Style getSpecificStyle(String name);

    Style update(int id, Style country);

    Style createStyle(Style newStyle);

    void deleteStyle(String name);
}
