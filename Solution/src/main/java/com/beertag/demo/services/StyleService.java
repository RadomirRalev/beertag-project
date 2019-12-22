package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Style;

import java.util.List;

public interface StyleService {
    Style getStyleById(int id);

    List<Style> getStylesList();

    List<Style> getStyleByName(String name);

    Style update(int id, Style country);

    Style createStyle(Style newStyle);

    List<Beer> getBeersByStyleId(int styleId);

    void deleteStyle(int id);
}
