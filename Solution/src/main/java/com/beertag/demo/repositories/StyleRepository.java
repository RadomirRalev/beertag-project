package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Style;

import java.util.List;

public interface StyleRepository {
    Style getStyleById(int id);

    List<Style> getStylesList();

    Style getSpecificStyle(String name);

    Style update(int id, Style country);

    Style createStyle(Style newStyle);

    boolean checkStyleExists(String name);

    void deleteStyle(String name);
}
