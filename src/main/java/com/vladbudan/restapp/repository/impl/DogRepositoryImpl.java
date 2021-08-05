package com.vladbudan.restapp.repository.impl;

import com.vladbudan.restapp.exception.RepositoryException;
import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.repository.DogRepository;
import com.vladbudan.restapp.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;
import static java.util.Objects.isNull;

public class DogRepositoryImpl implements DogRepository {

    Logger log = getLogger(DogRepositoryImpl.class.getName());

    @Override
    public Dog save(Dog dog) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(dog);

            transaction.commit();
        } catch(Exception e) {
            if (!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("Dog didn't save!");
        }
        return dog;
    }

    @Override
    public Dog update(Dog dog) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(dog);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("Dog didn't update!");
        }
        return dog;
    }

    @Override
    public void delete(Integer id) {
        Transaction transaction = null;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Dog dog = session.get(Dog.class, id);

            session.delete(dog);

            transaction.commit();
        }
        catch(Exception e) {
            if(!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("Dog didn't delete!");
        }
    }

    @Override
    public Optional<Dog> getDogById(Integer id) {
        Transaction transaction = null;
        Dog dog;
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            dog = session.get(Dog.class, id);

            transaction.commit();
        }
        catch(Exception e) {
            if(!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("Dog didn't find by id!");
        }
        return Optional.ofNullable(dog);
    }

    @Override
    public List<Dog> getAllDog() {
        Transaction transaction = null;
        List<Dog> dogs = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            dogs = session.createQuery("from Dog", Dog.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (!isNull(transaction)) {
                transaction.rollback();
            }
            log.info(e.getMessage());
            throw new RepositoryException("All dogs didn't find!");
        }
        return dogs;
    }
}
