package com.cinema.controller;

import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;
import com.cinema.service.MapperToDto;
import com.cinema.service.MapperToEntity;
import com.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MapperToDto<MovieSession, MovieSessionResponseDto> mapperToDto;
    private final MapperToEntity<MovieSession, MovieSessionRequestDto> mapperToEntity;
    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieSessionController(MapperToDto<MovieSession, MovieSessionResponseDto> mapperToDto,
                                  MapperToEntity<MovieSession,
                                          MovieSessionRequestDto> mapperToEntity,
                                  MovieSessionService movieSessionService) {
        this.mapperToDto = mapperToDto;
        this.mapperToEntity = mapperToEntity;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailable(@RequestParam Long movieId,
                                                      @RequestParam @DateTimeFormat
                                                              (pattern = "dd.MM.yyyy")
                                                              LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(mapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(mapperToEntity.mapToEntity(movieSessionRequestDto));
    }

    @PutMapping("/{id}")
    public void updateMovieSession(@PathVariable Long id,
                                   @RequestBody @Valid
                                           MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = mapperToEntity.mapToEntity(movieSessionRequestDto);
        movieSession.setId(id);
        movieSessionService.update(movieSession);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieSession(@PathVariable Long id) {
        movieSessionService.delete(id);
    }
}
