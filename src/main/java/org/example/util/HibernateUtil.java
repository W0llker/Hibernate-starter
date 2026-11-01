package org.example.util;

import org.example.converter.RegisteredConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        var configuration = new Configuration().configure();
        configuration.addAttributeConverter(new RegisteredConverter());
        return configuration.buildSessionFactory();
    }
}
