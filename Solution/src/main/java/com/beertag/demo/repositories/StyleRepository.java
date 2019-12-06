package com.beertag.demo.repositories;

import com.beertag.demo.models.Style;

import java.util.List;

public interface StyleRepository {
    Style getStyleById(int id);

    List<Style> getStylesList();

    Style getSpecificStyle(String name);

    void update(int id, Style country);

    void createStyle(Style newStyle);

    boolean checkStyleExists(String name);

    void deleteStyle(String name);
}
