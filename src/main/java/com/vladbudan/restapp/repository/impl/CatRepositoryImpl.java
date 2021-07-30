package com.vladbudan.restapp.repository.impl;

import com.vladbudan.restapp.exception.RepositoryException;
import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.repository.CatRepository;
import com.vladbudan.restapp.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;
import static java.util.Objects.isNull;

public class CatRepositoryImpl implements CatRepository {

    Logger log = getLogger(CatRepositoryImpl.class.getName());

    @Override
    public Cat save(Cat cat) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(cat);

            transaction.commit();
        } catch(Exception e) {
            if (!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("Cat didn't save!");
        }
        return cat;
    }

    @Override
    public Cat update(Cat cat) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(cat);

            transaction.commit();
        }
        catch(Exception e) {
            if(!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("Cat didn't update!");
        }
        return cat;
    }

    @Override
    public void delete(Integer id) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Cat cat = session.get(Cat.class, id);

            session.delete(cat);

            transaction.commit();
        }
        catch(Exception e) {
            if(!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("Cat didn't delete!");
        }
    }

    @Override
    public Optional<Cat> getCatById(Integer id) {
        Transaction transaction = null;
        Cat cat = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            cat = session.get(Cat.class, id);

            transaction.commit();
        }
        catch(Exception e) {
            if(!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("Cat didn't find by id!");
        }
        return Optional.ofNullable(cat);
    }

    @Override
    public List<Cat> getAllCat() {
        Transaction transaction = null;
        List<Cat> cats = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            cats = session.createQuery("from Cat", Cat.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("All cats didn't find!");
        }
        return cats;
    }

}
