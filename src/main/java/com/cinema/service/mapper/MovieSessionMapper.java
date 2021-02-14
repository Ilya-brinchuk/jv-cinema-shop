package com.cinema.service.mapper;

import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MapperDto;
import com.cinema.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper implements MapperDto<MovieSessionResponseDto, MovieSession,
        MovieSessionRequestDto> {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @Override
    public MovieSessionResponseDto replaceWithDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(movieSession.getDateTime().toString());
        return movieSessionResponseDto;
    }

    @Override
    public MovieSession replaceWithEntity(MovieSessionRequestDto requestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setId(requestDto.getMovieSessionId());
        movieSession.setMovie(movieService.get(requestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(requestDto.getCinemaHallId()));
        movieSession.setDateTime(LocalDateTime.parse(requestDto.getShowTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return movieSession;
    }
}
