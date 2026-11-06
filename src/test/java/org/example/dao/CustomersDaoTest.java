package org.example.dao;

import com.mysema.commons.lang.Assert;
import lombok.Cleanup;
import org.assertj.core.api.Assertions;
import org.example.util.HibernateUtil;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;



public class CustomersDaoTest {

    @Test
    public void findLimitedCustomersOrderByFullNameTest() {
        var dao = CustomersDao.getInstance();
        @Cleanup
        var session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        System.out.println(dao.findLimitedCustomersOrderByCreatedData(session, 3));
        session.getTransaction().commit();
    }

    @Test
    public void findByProductsByCategoriesNameTest() {
        var dao = CustomersDao.getInstance();
        @Cleanup
        var session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        System.out.println(dao.findByProductsByCategoriesName("Электроника", session));
        session.getTransaction().commit();
    }

    @Test
    public void testFindByFullNameWithQueryDSL() {
        var dao = CustomersDao.getInstance();
        @Cleanup
        var session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        System.out.println(dao.findAllByName("Мария Сидорова", session));
        session.getTransaction().commit();
    }

    @Test
    public void findByCustomersNameAndCountOrderTest() {
        var dao = CustomersDao.getInstance();
        @Cleanup
        var session = HibernateUtil.buildSessionFactory().openSession();

        session.beginTransaction();
        var result = dao.findByCustomersNameAndCountOrder(session);
        var name = result.stream().map(a -> a.get(0, String.class)).toList();


        Assertions.assertThat(name).contains("Ольга Лукашевич","Алексей Козлов",
                "Сергей Мороз","Татьяна Мельник","Иван Петров",
                "Елена Новик","Мария Сидорова","Дмитрий Волков",
                "Павел Гриб","Оксана Рудь");


        session.getTransaction().commit();
    }

    @Test
    public void testFindAllWithQueryDSL() {
        var dao = CustomersDao.getInstance();
        @Cleanup
        var session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        System.out.println(dao.findAllWithQueryDSL(session));
        session.getTransaction().commit();
    }

    @Test
    public void testFindAll() {
        var dao = CustomersDao.getInstance();
        @Cleanup
        var session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        System.out.println(dao.findAll(session));
        session.getTransaction().commit();
    }


    @Test
    public void testFindAllByName() {
        var dao = CustomersDao.getInstance();
        @Cleanup
        var session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        var result = dao.findAllByName("Ольга Лукашевич", session);
        System.out.println(result);
        session.getTransaction().commit();
    }
}
