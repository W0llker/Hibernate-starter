package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    static void main() {
        //TRANSIENT
        var customers = Customers.builder()
                .fullName("Машка 1231")
                .customersInfo(CustomersInfo.builder()
                        .registeredAt(new Registered(LocalDate.now()))
                        .city("Гомель")
                        .email("reklwos@gmail.com")
                        .build())
                .id(19L)
                .role(Role.ADMIN)
                .build();

        var order = Order.builder()
                .status(Status.delivered)
                .orderDate(LocalDate.now())
                .totalAmount(null)
                .customer(customers)
                .build();


        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
        // todo Работа с пользователем
//
//            try (var sessionOne = sessionFactory.openSession()) {
//                sessionOne.beginTransaction();
//                //Persistent к session1, но Transient к session2
//                sessionOne.save(customers);
//
//                customers.setFullName("Вы аыа");
//                log.warn("Customers update: {}", customers);
//                //загружает измененные данные в бд
////                sessionOne.merge(customers);
//                //обновляет данные из бд если вдруг мы поменяли что либо
////                sessionOne.refresh(customers);
//                //проверяет есть ли измененные данные, но не засинхронизированные с БД,
//                // но если пользователя нету в кэше то нету смысла какого либо от это метода
//                sessionOne.isDirty();
//                log.debug("Customers : {}, session {}", customers, sessionOne);
//                sessionOne.getTransaction().commit();
//            }
//            try (var sessionTwo = sessionFactory.openSession()) {
//                sessionTwo.beginTransaction();
//                //Сначала GET -> DELETE PERSISTENT k session2 DETACHED k session1
//                sessionTwo.delete(customers);
//                //REMOVED k session2
//                sessionTwo.getTransaction().commit();
//            }
            try (var session = sessionFactory.openSession()) {
                //todo работа пользователя с ордером
                session.beginTransaction();
                var orderTest = session.get(Order.class, 11L);
                System.out.println(orderTest);
//                session.save(customers);
//                session.save(order);;
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

}

