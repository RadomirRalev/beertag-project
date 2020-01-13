package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beertag.demo.constants.ExceptionConstants.*;
import static com.beertag.demo.constants.SQLQueryConstants.*;

@Repository
public class TagRepositoryImpl implements TagRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public TagRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Tag getTagById(int id) {
        try(Session session = sessionFactory.openSession()) {
            Tag tag = session.get(Tag.class, id);
            if (tag == null || tag.getStatus() != ENABLE) {
                throw new EntityNotFoundException(
                        String.format(TAG_ID_NOT_FOUND, id));
            }
            return tag;
        }
    }

    @Override
    public List<Tag> getTagList() {
        try(Session session = sessionFactory.openSession()) {
            Query<Tag> query = session.createQuery("from Tag where status = :status", Tag.class)
                    .setParameter("status", ENABLE);
            return query.list();
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public Tag update(int id, Tag tagToUpdate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(tagToUpdate);
            session.getTransaction().commit();
            return tagToUpdate;
        }
    }

    @Override
    public Tag createTag(Tag newTag) {
        try (Session session = sessionFactory.openSession()) {
            session.save(newTag);
        }
        return newTag;
    }

    @Override
    public boolean checkTagExists(String name) {
        return getTagByName(name).size() != 0;
    }

    @Override
    public void deleteTag(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update Tag " +
                    "set status = :status where id = :id ")
                    .setParameter("id", id)
                    .setParameter("status", DISABLE)
                    .executeUpdate();
            session.
                    getTransaction()
                    .commit();
        }
    }

    @Override
    public List<Tag> getTagByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session.createQuery("from Tag " +
                    "where name LIKE :name and status = :status", Tag.class);
            query.setParameter("name", "%" + name + "%");
            query.setParameter("status", ENABLE);
            return query.list();
        } catch (Exception e) {
            throw new EntityNotFoundException(TAG_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public void addTagToBeer(int tagId, int beerId) {
        try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                Tag tag = session.get(Tag.class,tagId);
                Beer beer = session.get(Beer.class,beerId);
                beer.getTags().add(tag);
                session.update(beer);
                session.getTransaction().commit();
        }
    }

    @Override
    public void removeTagFromBeer(int tagId, int beerId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Tag tag = session.get(Tag.class,tagId);
            Beer beer = session.get(Beer.class,beerId);
            beer.getTags().remove(tag);
            session.update(beer);
            session.getTransaction().commit();
        }
    }
}
