package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Style;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beertag.demo.constants.ExceptionConstants.*;
import static com.beertag.demo.constants.SQLQueryConstants.*;

@Repository
public class StyleRepositoryImpl implements StyleRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public StyleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Style getStyleById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Style style = session.get(Style.class, id);
            if (style == null || style.getStatus() != ENABLE) {
                throw new EntityNotFoundException(
                        String.format(STYLE_ID_NOT_FOUND, id));
            }
            return style;
        }
    }

    @Override
    public List<Style> getStylesList() {
        try (Session session = sessionFactory.openSession()) {
            Query<Style> query = session.createQuery("from Style where status = :status", Style.class)
                    .setParameter("status", ENABLE);
            return query.list();
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public List<Style> getStyleByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Style> query = session.createQuery("from Style " +
                    "where name LIKE :name and status = :status", Style.class);
            query.setParameter("name", "%" + name + "%");
            query.setParameter("status", ENABLE);
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
            session.createQuery("update Style " +
                    "set status = :status where id = :id ")
                    .setParameter("id", id)
                    .setParameter("status", DISABLE)
                    .executeUpdate();
            session.
                    getTransaction()
                    .commit();
        }
    }
}
