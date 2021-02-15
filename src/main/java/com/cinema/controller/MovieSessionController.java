package com.cinema.controller;

import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;
import com.cinema.service.DtoMapper;
import com.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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
    private final DtoMapper<MovieSessionResponseDto, MovieSession,
                MovieSessionRequestDto> dtoMapper;
    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieSessionController(DtoMapper<MovieSessionResponseDto, MovieSession,
            MovieSessionRequestDto> dtoMapper, MovieSessionService movieSessionService) {
        this.dtoMapper = dtoMapper;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailable(@RequestParam Long movieId,
                                                      @RequestParam @DateTimeFormat
                                                              (pattern = "dd.MM.yyyy")
                                                              LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(dtoMapper.mapToEntity(movieSessionRequestDto));
    }

    @PutMapping("/{id}")
    public void updateMovieSession(@PathVariable Long id,
                                   @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = dtoMapper.mapToEntity(movieSessionRequestDto);
        movieSession.setId(id);
        movieSessionService.update(movieSession);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieSession(@PathVariable Long id) {
        movieSessionService.delete(id);
    }
}
