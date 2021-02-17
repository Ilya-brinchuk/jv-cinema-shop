package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponseDto;
import com.cinema.service.MapperToDto;
import com.cinema.service.MapperToEntity;
import com.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MapperToDto<Movie, MovieResponseDto> mapperToDto;
    private final MapperToEntity<Movie, MovieRequestDto> mapperToEntity;
    private final MovieService movieService;

    @Autowired
    public MovieController(MapperToDto<Movie, MovieResponseDto> mapperToDto,
                           MapperToEntity<Movie, MovieRequestDto> mapperToEntity,
                           MovieService movieService) {
        this.mapperToDto = mapperToDto;
        this.mapperToEntity = mapperToEntity;
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(mapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody @Valid MovieRequestDto movieRequestDto) {
        movieService.add(mapperToEntity.mapToEntity(movieRequestDto));
    }
}
