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

    @Override
    public Style getSpecificStyle(String name) {
        return getStyle(name);
    }

    @Override
    public void update(int id, Style country) {
        for (int i = 0; i < stylesList.size(); i++) {
            if (stylesList.get(i).getId() == id) {
                stylesList.set(i, country);
                break;
            }
        }
    }

    @Override
    public void createStyle(Style newStyle) {
        stylesList.add(newStyle);
    }

    @Override
    public boolean checkStyleExists(String name) {
        return stylesList.stream()
                .anyMatch(style -> style.getName().equals(name));
    }

    @Override
    public void deleteStyle(String name) {
        Style styleToBeRemoved = getStyle(name);
        stylesList.remove(styleToBeRemoved);
    }

    private Style getStyle(String name) {
        return stylesList.stream()
                .filter(style -> style.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Style %s not found in the database", name)));
    }

}
