package com.project.utils;

import com.project.pojo.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        Properties props = new Properties();
        props.put(Environment.DIALECT,"org.hibernate.dialect.SQLServer2008Dialect");
        props.put(Environment.DRIVER,"com.microsoft.sqlserver.jdbc.SQLServerDriver");
        props.put(Environment.URL,"jdbc:sqlserver://localhost:1433;database=ShoppingDB");
        props.put(Environment.USER,"sa");
        props.put(Environment.PASS,"121212");
        props.put(Environment.SHOW_SQL,"true");

        configuration.setProperties(props);
        configuration.addAnnotatedClass(Products.class);
        configuration.addAnnotatedClass(Orders.class);
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(OrdersDetail.class);
        configuration.addAnnotatedClass(SubcribleEmail.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(registry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
