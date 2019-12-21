package com.beertag.demo.repositories;
import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Style;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Repository
public class StyleRepositoryImpl implements StyleRepository {
    private static int styleID;
    private List<Style> stylesList;
    private SessionFactory sessionFactory;

    @Autowired
    public StyleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Style getStyleById(int id) {
        try(Session session = sessionFactory.openSession()) {
            Style style = session.get(Style.class, id);
            if (style == null) {
                throw new EntityNotFoundException(
                        String.format(STYLE_ID_NOT_FOUND, id));
            }
            return style;
        }
    }

    @Override
    public List<Style> getStylesList() {
        try(Session session = sessionFactory.openSession()) {
            Query<Style> query = session.createQuery("from Style", Style.class);
            return query.list();
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public Style getStyleByName(String name) {
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
