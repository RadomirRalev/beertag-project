package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.DrankList;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.WishList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.beertag.demo.constants.ExceptionConstants.*;
import static com.beertag.demo.constants.SQLQueryConstants.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

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
    public Set<Beer> getWishList(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createNativeQuery(GET_WISHLIST_SQL, Beer.class)
                    .setParameter("username", username)
                    .setParameter("status", ENABLE);
            return new HashSet<>(query.list());
        }
    }

    @Override
    public void addBeerFromWishList(WishList wishList) {
        try (Session session = sessionFactory.openSession()) {
            session.save(wishList);
        }
    }

    @Override
    public void setStatusWishList(String username, int beerId, int status) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update WishList " +
                    "set status = :status " +
                    "where beerId = :beerId and username = :username ")
                    .setParameter("beerId", beerId)
                    .setParameter("username", username)
                    .setParameter("status", status)
                    .executeUpdate();
            session.getTransaction()
                    .commit();
        }
    }

    @Override
    public boolean isUserHaveCurrentBeerOnWishList(String username, int beerId) {
        try (Session session = sessionFactory.openSession()) {
            return !session.createQuery("from WishList " +
                    "where username = :username and beerId = :beerId ", WishList.class)
                    .setParameter("username", username)
                    .setParameter("beerId", beerId)
                    .list().isEmpty();
        }
    }

    public boolean isBeerEnabletOnWishList(String username, int beerId) {
        try (Session session = sessionFactory.openSession()) {
            return !session.createQuery("from WishList " +
                    "where username = :username and beerId = :beerId and status = :status ", WishList.class)
                    .setParameter("username", username)
                    .setParameter("beerId", beerId)
                    .setParameter("status", ENABLE)
                    .list().isEmpty();
        }
    }


    @Override
    public Set<Beer> getDrankList(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createNativeQuery(GET_DRANKLIST_SQL, Beer.class)
                    .setParameter("username", username)
                    .setParameter("status", ENABLE);
            return new HashSet<>(query.list());
        }
    }


    @Override
    public void addBeerToDrankList(DrankList drankList) {
        try (Session session = sessionFactory.openSession()) {
            session.save(drankList);
        }
    }

    @Override
    public void setStatusDrankList(String username, int beerId, int status) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update DrankList " +
                    "set status = :status " +
                    "where beerId = :beerId and username = :username ")
                    .setParameter("beerId", beerId)
                    .setParameter("username", username)
                    .setParameter("status", status)
                    .executeUpdate();
            session.getTransaction()
                    .commit();
        }
    }

    @Override
    public boolean isUserHaveCurrentBeerOnDrankList(String username, int beerId) {
        try (Session session = sessionFactory.openSession()) {
            return !session.createQuery("from DrankList " +
                    "where username = :username and beerId = :beerId", DrankList.class)
                    .setParameter("username", username)
                    .setParameter("beerId", beerId)
                    .list().isEmpty();
        }
    }

    public boolean isBeerEnabletOnDrankList(String username, int beerId) {
        try (Session session = sessionFactory.openSession()) {
            return !session.createQuery("from DrankList " +
                    "where username = :username and beerId = :beerId and status = :status ", DrankList.class)
                    .setParameter("username", username)
                    .setParameter("beerId", beerId)
                    .setParameter("status", ENABLE)
                    .list().isEmpty();
        }
    }

    @Override
    public void rateBeer(String username, int beerId, int rating) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = String.format(RATE_BEER_SQL, rating, username, beerId);
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public User createUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            String sql = String.format(INSERT_USER_ROLE_SQL, user.getUsername());
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
        return user;
    }

    @Override

    public User getByUsername(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(GET_ENABLE_USER_SQL, User.class);
            query.setParameter("name", name);
            query.setParameter("status", ENABLE);
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
            if (user == null || user.getEnabled() != ENABLE) {
                throw new EntityNotFoundException(USER_WITH_ID_NOT_FOUND, id);
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
    public void setStatusUser(String username,int status) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update User " +
                    "set enabled = :status where username = :username ")
                    .setParameter("username", username)
                    .setParameter("status", status)
                    .executeUpdate();
            session.
                    getTransaction()
                    .commit();
        }
    }

    @Override
    public boolean usernameExist(String name) {
        try (Session session = sessionFactory.openSession()) {
            return !session.createQuery("from User " +
                    "where username = :name and enabled = :status", User.class)
                    .setParameter("name", name)
                    .setParameter("status", ENABLE)
                    .list().isEmpty();
        }
    }

    @Override
    public boolean emailExist(String email) {
        try (Session session = sessionFactory.openSession()) {
            return !session.createQuery("from User " +
                    "where email = :email and enabled = :status", User.class)
                    .setParameter("email", email)
                    .setParameter("status", ENABLE)
                    .list().isEmpty();
        }
    }
}