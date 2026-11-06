package org.example;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;


import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDate;


class HibernateRunnerTest {

//    @Test
    // a - стоимость тарифа, b - размер тарифа, c - стоимость каждого лишнего мгбт, d - размер трафика
//    void paymentTraffik(int a, int b, int c, int d) {
//        return b < d ? a : a + (c * (b - d));
//    }

//    @Test
//    void testPancake(int n) {
//        int result = 0;
//        while (n != 0) {
//            n = n / 2;
//            result += 1;
//        }
//        return result;
//    }

    @Test
    public void getProductAndCategory() {
        @Cleanup
        var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup
        var session = sessionFactory.openSession();

        session.beginTransaction();
//        var product = session.createQuery("from Products").list();
//        System.out.println(product);
        System.out.println("***************************************");
        var pr = session.createQuery("from Categories c where c.id = 1", Categories.class).list();
        System.out.println(pr);

    }

    @Test
    public void checkH2() {

        @Cleanup
        var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup
        var session = sessionFactory.openSession();

        session.beginTransaction();
        //Вывод HQL
//        var customers = session.createQuery("select c from Customers c").list();
//        System.out.println(customers.toString());

        //CriteriaBuilder
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Customers> criteriaQuery = cb.createQuery(Customers.class);
//        criteriaQuery.select(criteriaQuery.from(Customers.class));
//        var customers = session.createQuery(criteriaQuery).list();
        //Обычный SQL
//        var customers = session.createNativeQuery("SELECT * FROM customers", Customers.class).list();

        long id = 3;
        //todo 1 вариант типизации
//        var customers = session.createQuery("select c from Customers c where c.id = ?1", Customers.class)
//                .setParameter(1, id)
//                .getSingleResult();
        //todo 2 вариант типизации
//        var cust = session.createQuery("select c from Customers c where c.id = :id", Customers.class)
//                .setParameter("id", id)
//                .getResultList();
        TypedQuery<Customers> customers = session.createQuery("from Customers c where c.id = :id", Customers.class)
                .setParameter("id", id);
        var customer = customers.getSingleResult();

        System.out.println(customer);
        session.getTransaction().commit();

    }

    @Test
    void addNewCustomerAndOrder() {
        @Cleanup
        var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup
        var session = sessionFactory.openSession();
        session.beginTransaction();

        var customers = Customers.builder()
                .fullName("Василий gdg")
                .customersInfo(CustomersInfo.builder()
                        .registeredAt(new Registered(LocalDate.now()))
                        .city("Гомель")
                        .email("reklwos@gmail.com")
                        .build())
                .id(19L)
                .role(Role.ADMIN)
                .build();

        session.getTransaction().commit();
    }

    @Test
    void checkOneToMany() {
        @Cleanup
        var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup
        var session = sessionFactory.openSession();

        session.beginTransaction();
        var user = session.get(Customers.class, 1L);
        System.out.println(user.getOrders());
        session.getTransaction().commit();

    }

    @Test
    @SneakyThrows
    public void testHibernateAPI() {
//        var customers = Customers.builder()
//                .fullName("Василий gdg")
//                .customersInfo(CustomersInfo.builder()
//                        .registeredAt(new Registered(LocalDate.now()))
//                        .city("Гомель")
//                        .email("reklwos@gmail.com")
//                        .build())
//                .id(19L)
//                .role(Role.ADMIN)
//                .build();
//
//        var sql = """
//                insert into
//                %s
//                (%s)
//                values
//                (%s)
//                """;
//
//        var tableName = Optional.ofNullable(customers.getClass().getAnnotation(Table.class))
//                .map(table -> table.name())
//                .orElse(customers.getClass().getName());
//
//        Field[] fields = customers.getClass().getDeclaredFields();
//
//        var columnNames = Arrays.stream(fields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName())
//                ).collect(Collectors.joining(", "));
//
//        var columnValues = Arrays.stream(fields)
//                .map(field -> "?")
//                .collect(Collectors.joining(", "));
//
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
//                "postgres",
//                "admin");
//        PreparedStatement statement = connection
//                .prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//
//        for (int i = 0; i < fields.length; i++) {
//            //для работы с приватными полями
//            fields[i].setAccessible(true);
//            statement.setObject(i + 1, fields[i].get(customers));
//        }
//        System.out.println(statement);
//        statement.executeUpdate();
//        statement.close();
//        connection.close();
    }
}