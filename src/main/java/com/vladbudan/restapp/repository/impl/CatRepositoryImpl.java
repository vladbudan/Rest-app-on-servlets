package com.vladbudan.restapp.repository.impl;

import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.repository.CatRepository;
import com.vladbudan.restapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    public CatRepositoryImpl() {
    }

    @Override
    public Cat save(Cat cat) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(cat);

            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cat;
    }

    @Override
    public void update(Cat cat) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(cat);

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
        Cat cat = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            cat = session.get(Cat.class, id);

            session.delete(cat);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Cat getById(Integer id) {
        Transaction transaction = null;
        Cat cat = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            cat = session.get(Cat.class, id);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return cat;
    }

    @Override
    public List<Cat> getAllCat() {
        Transaction transaction = null;
        List<Cat> cats = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            cats = session.createQuery("from Cat", Cat.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return cats;
    }
}
