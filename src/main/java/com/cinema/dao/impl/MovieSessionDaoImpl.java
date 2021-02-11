package com.cinema.dao.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save in data base this movie session: "
                    + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        String hql = "SELECT m FROM MovieSession m WHERE m.movie.id = :movieId "
                + "AND DATE_FORMAT(m.dateTime, '%Y-%m-%d') = :date";
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery(hql);
            query.setParameter("movieId", movieId);
            query.setParameter("date", date.format(DateTimeFormatter.ISO_DATE));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available sessions by this id: "
                    + movieId + "and by this time" + date.toString(), e);
        }
    }

}
