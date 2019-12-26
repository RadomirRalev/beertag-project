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
    public List<User> showUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    @Override
    public List<Beer> getWishList() {
        return null;
    }

    @Override
    public List<Beer> getDrankList(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createNativeQuery("select * from beer" +
                    " inner join drank_beer on beer.beer_id = drank_beer.drank_beer_beer_id" +
                    " where drank_beer_user_id = :userId", Beer.class);
            query.setParameter("userId", userId);
            return query.list();
        }
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
