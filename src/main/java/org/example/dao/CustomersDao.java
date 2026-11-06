package org.example.dao;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.entity.Customers;
import org.example.entity.Products;

import org.example.entity.QCategories;
import org.example.entity.QOrder;
import org.hibernate.Session;

import java.util.List;


import static org.example.entity.QCategories.*;
import static org.example.entity.QCustomers.*;
import static org.example.entity.QProducts.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomersDao {
    private static final CustomersDao INSTANCE = new CustomersDao();

    public List<Customers> findAllWithQueryDSL(Session session) {
        return new JPAQuery<Customers>(session).select(customers).from(customers).fetch();
    }

    public List<Customers> findAll(Session session) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(Customers.class);
        var custm = criteria.from(Customers.class);

        criteria.select(custm);

        return session.createQuery(criteria).list();
    }



    public List<Customers> findLimitedCustomersOrderByCreatedData(Session session, int limit) {
        return new JPAQuery<Customers>(session).select(customers).from(customers)
//                .orderBy(customers.fullName.desc()) -> для обычный полей
                .orderBy(new OrderSpecifier(Order.ASC, customers.customersInfo().registeredAt)) // для необычных
                .limit(limit)
                .fetch();

    }

    public List<Customers> findAllByName(String name, Session session) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(Customers.class);
        var custm = criteria.from(Customers.class);

        criteria.select(custm).where(cb.equal(custm.get("fullName"), name));

        return session.createQuery(criteria).list();
    }

    public List<Products> findByProductsByCategoriesName(String categori, Session session) {
        return new JPAQuery<Products>(session)
                .select(products)
                .from(categories)
                .join(categories.products, products)
                .where(categories.name.eq(categori))
                .fetch();
    }

    public List<Tuple>  findByCustomersNameAndCountOrder(Session session) {
        return new JPAQuery<Tuple>(session)
                .select(customers.fullName, QOrder.order.id.count())
                .from(QOrder.order)
                .join(QOrder.order.customer(), customers)
                .groupBy(customers.fullName)
                .fetch();
    }

//    public List<Customers> findAllByNameWithHibernateJpaModel(String name, Session session) {
//        var cb = session.getCriteriaBuilder();
//        var criteria = cb.createQuery(Customers.class);
//        var custm = criteria.from(Customers.class);
//
//        criteria.select(custm).where(cb.equal(custm.get(Customers_.FULL_NAME), name));
//
//        return session.createQuery(criteria).list();
//    }


    public static CustomersDao getInstance() {
        return INSTANCE;
    }
}
