package com.cinema.service.mapper;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponseDto;
import com.cinema.service.MapperDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper implements MapperDto<CinemaHallResponseDto,
        CinemaHall, CinemaHallRequestDto> {
    @Override
    public CinemaHallResponseDto replaceWithDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }

    @Override
    public CinemaHall replaceWithEntity(CinemaHallRequestDto requestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(requestDto.getDescription());
        cinemaHall.setCapacity(requestDto.getCapacity());
        return cinemaHall;
    }

}
