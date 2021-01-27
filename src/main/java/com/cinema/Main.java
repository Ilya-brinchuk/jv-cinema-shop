package com.cinema;

import com.cinema.lib.Injector;
import com.cinema.model.Movie;
import com.cinema.service.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("com.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
