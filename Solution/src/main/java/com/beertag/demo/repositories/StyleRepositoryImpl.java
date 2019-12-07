package com.beertag.demo.repositories;
import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Style;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Repository
public class StyleRepositoryImpl implements StyleRepository {
    private static int styleID;
    private List<Style> stylesList;

    public StyleRepositoryImpl() {
        stylesList = new ArrayList<>();
        Style style = new Style("Special Ale");
        style.setId(StyleRepositoryImpl.styleID++);
        stylesList.add(style);
        style = new Style("English Porter");
        style.setId(StyleRepositoryImpl.styleID++);
        stylesList.add(style);
        style = new Style("Indian Pale Ale");
        style.setId(StyleRepositoryImpl.styleID++);
        stylesList.add(style);
    }

    @Override
    public Style getStyleById(int id) {
        return stylesList.stream()
                .filter(style -> style.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(STYLE_ID_NOT_FOUND, id));

    }

    @Override
    public List<Style> getStylesList() {
        try {
            return stylesList;
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public Style getSpecificStyle(String name) {
        try {
            return getStyle(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(STYLE_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Style update(int id, Style style) {
        for (int i = 0; i < stylesList.size(); i++) {
            if (stylesList.get(i).getId() == id) {
                stylesList.set(i, style);
                break;
            }
            if (i == stylesList.size() - 1) {
                throw new EntityNotFoundException(STYLE_ID_NOT_FOUND, id);
            }
        }
        return style;
    }

    @Override
    public Style createStyle(Style newStyle) {
        try {
            stylesList.add(newStyle);
            return newStyle;
        } catch (Exception e) {
            throw new DuplicateEntityException(STYLE_NAME_EXISTS, newStyle.getName());
        }
    }

    @Override
    public boolean checkStyleExists(String name) {
        return stylesList.stream()
                .anyMatch(style -> style.getName().equals(name));
    }

    @Override
    public void deleteStyle(String name) {
        try {
            Style styleToBeRemoved = getStyle(name);
            stylesList.remove(styleToBeRemoved);
        } catch (Exception e) {
            throw new EntityNotFoundException(STYLE_NAME_NOT_FOUND, name);
        }
    }

    private Style getStyle(String name) {
        return stylesList.stream()
                .filter(style -> style.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(STYLE_NAME_NOT_FOUND, name)));
    }

}
