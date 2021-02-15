package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponseDto;
import com.cinema.service.DtoMapper;
import com.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final DtoMapper<MovieResponseDto, Movie, MovieRequestDto> dtoMapper;
    private final MovieService movieService;

    @Autowired
    public MovieController(DtoMapper<MovieResponseDto, Movie, MovieRequestDto> dtoMapper,
                           MovieService movieService) {
        this.dtoMapper = dtoMapper;
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(dtoMapper.mapToEntity(movieRequestDto));
    }
}
