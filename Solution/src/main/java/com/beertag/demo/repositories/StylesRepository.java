package com.beertag.demo.repositories;

import com.beertag.demo.models.Style;

import java.util.List;

public interface StylesRepository {
    Style getStyleById(int id);

    List<Style> getStylesList();
}
