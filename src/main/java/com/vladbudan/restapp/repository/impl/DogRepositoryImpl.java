package com.vladbudan.restapp.repository.impl;

import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.repository.DogRepository;
import com.vladbudan.restapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DogRepositoryImpl implements DogRepository {

    @Override
    public Dog save(Dog dog) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(dog);

            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return dog;
    }

    @Override
    public void update(Dog dog) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(dog);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Transaction transaction = null;
        Dog dog = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            dog = session.get(Dog.class, id);

            session.delete(dog);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Dog getById(Integer id) {
        Transaction transaction = null;
        Dog dog = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            dog = session.get(Dog.class, id);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return dog;
    }

    @Override
    public List<Dog> getAllDog() {
        Transaction transaction = null;
        List<Dog> dogs = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            dogs = session.createQuery("from Dog", Dog.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return dogs;
    }
}
