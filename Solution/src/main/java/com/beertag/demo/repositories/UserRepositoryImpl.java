package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.beertag.demo.exceptions.Constants.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    @Override
    public Set<Beer> getWishList(int userId) {
        User user = getById(userId);
        if (user.getWishList().isEmpty()) {
            throw new EntityNotFoundException(USER_WISH_EMPTY, userId);
        }
        return user.getWishList();
    }

    @Override
    public Set<Beer> getDrankList(int userId) {
        User user = getById(userId);
        if (user.getDrankList().isEmpty()) {
            throw new EntityNotFoundException(USER_DRANK_EMPTY, userId);
        }
        return user.getDrankList();
    }

    @Override
    public User createUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        }
        return user;
    }

    @Override
    public User getByUsername(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User" +
                    " where username = :name and deleted = false ", User.class);
            query.setParameter("name", name);
            if (query.list().size() != 1) {
                throw new EntityNotFoundException(USER_USERNAME_NOT_FOUND, name);
            }
            return query.list().get(0);
        }
    }


    @Override
    public User getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user == null || user.isDeleted()) {
                throw new EntityNotFoundException(USER_ID_NOT_FOUND, id);
            }
            return user;
        }
    }

    @Override
    public User updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public void softDeleteUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update User " +
                    "set deleted = true " +
                    "where id = :id ");
            query.setParameter("id", user.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean userExist(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username = :name", User.class);
            query.setParameter("name", name);
            return query.list().size() != 0;
        }
    }

    @Override
    public boolean emailExist(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where email = :name", User.class);
            query.setParameter("name", email);
            return query.list().size() != 0;
        }
    }
}
