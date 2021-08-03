package com.vladbudan.restapp.repository.impl;

import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.repository.UserRepository;
import com.vladbudan.restapp.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;
import static java.util.Objects.isNull;

public class UserRepositoryImpl implements UserRepository {

    Logger log = getLogger(CatRepositoryImpl.class.getName());

    @Override
    public User save(User user) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch(Exception e) {
            if (!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
        return user;
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(user);

            transaction.commit();
        }
        catch(Exception e) {
            if(!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
        return user;
    }

    @Override
    public User delete(Integer id) {
        Transaction transaction = null;
        User user = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            user = session.get(User.class, id);

            session.delete(user);

            transaction.commit();
        }
        catch(Exception e) {
            if(!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
        return user;
    }

    @Override
    public Optional<User> getById(Integer id) {
        Transaction transaction = null;
        User user = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            user = session.createQuery("from User u where u.id = " + id, User.class).getSingleResult();

            transaction.commit();
        }
        catch (NoResultException e){
            // if there isn't user
        }
        catch(Exception e) {
            if(!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            users = session.createQuery("from User", User.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
        return users;
    }
}
