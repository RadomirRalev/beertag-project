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
    public List<Beer> getDrankList() {
        return null;
    }

    @Override
    public User createUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        }
        return user;
    }

    //TODO
    @Override
    public List<User> getByNickname(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where nickName like :name", User.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        }
    }


    @Override
    public User getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user == null) {
                throw new EntityNotFoundException(USER_ID_NOT_FOUND, id);
            }
            return user;
        }
    }

    @Override
    public void deleteUser(User user) {
//        try {
//            findUser(user.getNickName());
//            usersList.remove(user.getNickName());
//        } catch (Exception e) {
//            throw new EntityNotFoundException(USER_NAME_NOT_FOUND, user.getNickName());
//        }
    }

    @Override
    public User updateUser(User user) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public boolean userExist(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where nickName = :name", User.class);
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
