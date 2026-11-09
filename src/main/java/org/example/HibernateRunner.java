package org.example;


import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.CustomersDao;
import org.example.dto.CustomersRequest;
import org.example.entity.*;
import org.example.mapper.CustomerMapper;
import org.example.service.ServiceCustomers;
import org.example.util.HibernateUtil;

import javax.persistence.LockModeType;
import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
    static void main(String[] args) {
        ServiceCustomers serviceCustomers = new ServiceCustomers(CustomersDao.getInstance()
                , new CustomerMapper());

        CustomersRequest request = new CustomersRequest("Вася репкин", CustomersInfo.builder()
                .registeredAt(new Registered(LocalDate.now()))
                .city("Гомель")
                .email("reklwos@gmail.com")
                .build());
        serviceCustomers.addCustomer(request);


    }

    static void test() {


        @Cleanup
        var sessions = HibernateUtil.buildSessionFactory().openSession();
        sessions.beginTransaction();
        var categories = sessions.find(Categories.class, 1L, LockModeType.PESSIMISTIC_READ);
//        sessions.lock(categories, LockModeType.OPTIMISTIC);
//        sessions.createQuery("select c from Categories c where c.id = 1L").setLockMode(LockModeType.OPTIMISTIC);
        System.out.println(categories);

        sessions.getTransaction().commit();

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
//                sessionOne.save(categories);
//
//                categories.setFullName("Вы аыа");
//                log.warn("Customers update: {}", categories);
//                //загружает измененные данные в бд
////                sessionOne.merge(categories);
//                //обновляет данные из бд если вдруг мы поменяли что либо
////                sessionOne.refresh(categories);
//                //проверяет есть ли измененные данные, но не засинхронизированные с БД,
//                // но если пользователя нету в кэше то нету смысла какого либо от это метода
//                sessionOne.isDirty();
//                log.debug("Customers : {}, session {}", categories, sessionOne);
//                sessionOne.getTransaction().commit();
//            }
//            try (var sessionTwo = sessionFactory.openSession()) {
//                sessionTwo.beginTransaction();
//                //Сначала GET -> DELETE PERSISTENT k session2 DETACHED k session1
//                sessionTwo.delete(categories);
//                //REMOVED k session2
//                sessionTwo.getTransaction().commit();
//            }
            try (var session = sessionFactory.openSession()) {
                //todo работа пользователя с ордером
                session.beginTransaction();
                var orderTest = session.get(Order.class, 11L);
                System.out.println(orderTest);
//                session.save(categories);
//                session.save(order);;
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

}

