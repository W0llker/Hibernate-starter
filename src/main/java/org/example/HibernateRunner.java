package org.example;


import org.example.converter.RegisteredConverter;
import org.example.entity.Customers;
import org.example.entity.Registered;
import org.example.entity.Role;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    static void main() {
        Configuration configuration = new Configuration();
        configuration.configure();
//        configuration.addAnnotatedClass(Customers.class);
        //todo указывает на стратегию найменига бд keyBuilder -> key_builder
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAttributeConverter(new RegisteredConverter(),true);

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(Customers.builder()
                    .fullName("Машка Сашка")
                    .registeredAt(new Registered(LocalDate.of(2024, 1, 12)))
                    .city("Гомель")
                    .email("rekwl@gmail.com")
                    .id(19L)
                    .role(Role.ADMIN)
                    .build());

            session.getTransaction().commit();
        }

    }
}
