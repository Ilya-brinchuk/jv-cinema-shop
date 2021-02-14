package com.cinema.service.mapper;

import com.cinema.model.Movie;
import com.cinema.model.dto.MovieRequestDto;
import com.cinema.model.dto.MovieResponseDto;
import com.cinema.service.MapperDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements MapperDto<MovieResponseDto, Movie, MovieRequestDto> {
    @Override
    public MovieResponseDto replaceWithDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }

    @Override
    public Movie replaceWithEntity(MovieRequestDto requestDto) {
        Movie movie = new Movie();
        movie.setTitle(requestDto.getTitle());
        movie.setDescription(requestDto.getDescription());
        return movie;
    }

}
