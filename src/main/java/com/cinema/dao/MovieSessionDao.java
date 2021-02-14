package com.cinema.dao;

import com.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    MovieSession update(MovieSession movieSession);

    MovieSession delete(MovieSession movieSession);
}
