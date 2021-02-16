package com.cinema.service.mapper;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponseDto;
import com.cinema.service.MapperToDto;
import com.cinema.service.MapperToEntity;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements MapperToDto<Movie, MovieResponseDto>,
        MapperToEntity<Movie, MovieRequestDto> {
    @Override
    public MovieResponseDto mapToDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }

    @Override
    public Movie mapToEntity(MovieRequestDto requestDto) {
        Movie movie = new Movie();
        movie.setTitle(requestDto.getTitle());
        movie.setDescription(requestDto.getDescription());
        return movie;
    }
}
