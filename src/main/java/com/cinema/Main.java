package com.cinema;

import com.cinema.exception.AuthenticationException;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.User;
import com.cinema.security.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static final Injector injector = Injector.getInstance("com.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);
        System.out.println(movieService.getAll());

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(200);
        cinemaHall.setDescription("White Hall");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());

        MovieSession movieSession = new MovieSession();
        LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 29, 15, 0);
        movieSession.setMovie(movie);
        movieSession.setDateTime(localDateTime);
        movieSession.setCinemaHall(cinemaHall);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);

        LocalDate localDate = LocalDate.of(2020, 10, 29);
        System.out.println(movieSessionService.findAvailableSessions(movie.getId(), localDate));

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);

        User user = authenticationService.register("@gmail.com", "1234444");
        System.out.println(user.toString());
        try {
            System.out.println(authenticationService.login(user.getEmail(), "1234444"));
        } catch (AuthenticationException e) {
            throw new DataProcessingException("Incorrect username or password");
        }

    }
}
