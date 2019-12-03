package com.beertag.demo.repositories;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Style;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StylesRepositoryImpl implements StylesRepository{
    private static int styleID;
    private List<Style> stylesList;

    public StylesRepositoryImpl() {
        stylesList = new ArrayList<>();
        Style style = new Style("Special Ale");
        style.setId(StylesRepositoryImpl.styleID++);
        stylesList.add(style);
        style = new Style("English Porter");
        style.setId(StylesRepositoryImpl.styleID++);
        stylesList.add(style);
        style = new Style("Indian Pale Ale");
        style.setId(StylesRepositoryImpl.styleID++);
        stylesList.add(style);
    }

    @Override
    public Style getStyleById(int id) {
        return stylesList.stream()
                .filter(style -> style.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Style", id));
    }

    @Override
    public List<Style> getStylesList() {
        return stylesList;
    }
}
