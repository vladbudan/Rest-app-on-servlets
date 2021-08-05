package com.vladbudan.restapp.config;

import com.vladbudan.restapp.exception.HibernateConfigException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws HibernateConfigException {

        if (sessionFactory == null) {

            try {
                Configuration configuration = new Configuration();

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml").build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                throw new HibernateException("Some problems with hibernate config...");
            }
        }
        return sessionFactory;
    }

}
