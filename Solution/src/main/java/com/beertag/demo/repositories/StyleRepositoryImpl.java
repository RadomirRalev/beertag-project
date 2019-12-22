package com.beertag.demo.repositories;
import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Brewery;
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
    public List<Style> getStyleByName(String name) {
        try(Session session = sessionFactory.openSession()) {
            Query<Style> query = session.createQuery("from Style where name= :name", Style.class);
            query.setParameter(name, "name");
            return query.list();
        } catch (Exception e) {
            throw new EntityNotFoundException(STYLE_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Style update(int id, Style styleToUpdate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(styleToUpdate);
            session.getTransaction().commit();
            return styleToUpdate;
        }
    }

    @Override
    public Style createStyle(Style newStyle) {
        try (Session session = sessionFactory.openSession()) {
            session.save(newStyle);
        }
        return newStyle;
    }

    @Override
    public boolean checkStyleExists(String name) {
        return getStyleByName(name).size() != 0;

    }

    @Override
    public void deleteStyle(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Style styleToBeDeleted = session.get(Style.class, id);
            session.delete(styleToBeDeleted);
            session.getTransaction().commit();
        }
    }
}
