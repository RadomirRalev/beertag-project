package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.DrankList;
import com.beertag.demo.models.user.UserDetail;
import com.beertag.demo.models.user.WishList;
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
    BeerRepository beerRepository;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory, BeerRepository beerRepository) {
        this.sessionFactory = sessionFactory;
        this.beerRepository = beerRepository;
    }

    @Override
    public List<UserDetail> getUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<UserDetail> query = session.createQuery("from User", UserDetail.class);
            return query.list();
        }
    }

    @Override
    public Set<Beer> getWishList(String username) {
        UserDetail userDetail = getByUsername(username);
        return userDetail.getWishList();
    }

    @Override
    public void addBeerToWishList(WishList wishList) {

        try (Session session = sessionFactory.openSession()) {
            session.save(wishList);
        }
    }

    @Override
    public void softDeleteBeerToWishList(WishList wishList) {

    }


    @Override
    public Set<Beer> getDrankList(String username) {
        UserDetail userDetail = getByUsername(username);
        return userDetail.getDrankList();
    }

    @Override
    public void addBeerToDrankList(DrankList drankList) {
        try (Session session = sessionFactory.openSession()) {
            session.save(drankList);
        }
    }


    @Override
    public UserDetail createUser(UserDetail userDetail) {
        try (Session session = sessionFactory.openSession()) {
            session.save(userDetail);
        }
        return userDetail;
    }
//TODO
    @Override
    public UserDetail getByUsername(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserDetail> query = session.createQuery("from User" +
                    " where username = :name and deleted = false ", UserDetail.class);
            query.setParameter("name", name);
            if (query.list().size() != 1) {
                throw new EntityNotFoundException(USER_USERNAME_NOT_FOUND, name);
            }
            return query.list().get(0);
        }
    }


    @Override
    public UserDetail getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            UserDetail userDetail = session.get(UserDetail.class, id);
            if (userDetail == null || userDetail.isDeleted()) {
                throw new EntityNotFoundException(USER_ID_NOT_FOUND, id);
            }
            return userDetail;
        }
    }

    @Override
    public UserDetail updateUser(UserDetail userDetail) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(userDetail);
            session.getTransaction().commit();
            return userDetail;
        }
    }

    @Override
    public void softDeleteUser(UserDetail userDetail) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update UserDetail " +
                    "set deleted = true " +
                    "where id = :id ")
                    .setParameter("id", userDetail.getId())
                    .executeUpdate();

            session.getTransaction()
                    .commit();
        }
    }

    @Override
    public boolean usernameExist(String name) {
        try (Session session = sessionFactory.openSession()) {
            return !session.createQuery("from User where username = :name", UserDetail.class)
                    .setParameter("name", name)
                    .list().isEmpty();
        }
    }
//TODO
    @Override
    public boolean emailExist(String email) {
        try (Session session = sessionFactory.openSession()) {
            return !session.createQuery("from User where email = :email", UserDetail.class)
                    .setParameter("email", email)
                    .list().isEmpty();
        }
    }
}
