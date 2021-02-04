package com.cinema.dao.impl;

import com.cinema.dao.OrderDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.Order;
import com.cinema.model.User;
import com.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save in data base this order: "
                    + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        String hql = "FROM Order o LEFT JOIN FETCH o.tickets "
                + "WHERE o.user = :user";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery(hql, Order.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find  shopping cart by this user : "
                    + user.getEmail(), e);
        }
    }
}
