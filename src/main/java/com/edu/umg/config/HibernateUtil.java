package com.edu.umg.config;

/**
 *
 * @author jose5
 */

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.edu.umg.extraerproperties.CargarPropiedades;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                CargarPropiedades cargarPropiedades = new CargarPropiedades();
                
                
                // Configuración de Hibernate
                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url", cargarPropiedades.getUrl());
                configuration.setProperty("hibernate.connection.username",cargarPropiedades.getUser());
                configuration.setProperty("hibernate.connection.password", cargarPropiedades.getPassword());
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");


                // Añadir la clase de entidad
                configuration.addAnnotatedClass(com.edu.umg.entities.Student.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

