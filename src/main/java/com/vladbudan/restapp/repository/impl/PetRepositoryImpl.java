package com.vladbudan.restapp.repository.impl;

import com.vladbudan.restapp.model.Pet;
import com.vladbudan.restapp.repository.PetRepository;
import com.vladbudan.restapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PetRepositoryImpl implements PetRepository {

    @Override
    public Pet save(Pet pet) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(pet);
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return pet;
    }

    @Override
    public void update(Pet pet) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(pet);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Pet delete(Integer id) {
        Transaction transaction = null;
        Pet pet = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            pet = session.get(Pet.class, id);

            session.delete(pet);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return pet;
    }

    @Override
    public Pet getById(Integer id) {
        Transaction transaction = null;
        Pet pet = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            pet = session.get(Pet.class, id);

            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return pet;
    }

    @Override
    public List<Pet> getAll() {
        Transaction transaction = null;
        List<Pet> pets = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            pets = session.createQuery("from Pet", Pet.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return pets;
    }
}
