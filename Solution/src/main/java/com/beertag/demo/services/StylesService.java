package com.beertag.demo.services;

import com.beertag.demo.models.Style;

import java.util.List;

public interface StylesService {
    Style getStyleById(int id);

    List<Style> getStylesList();
}
