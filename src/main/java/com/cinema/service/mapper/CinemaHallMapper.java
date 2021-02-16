package com.cinema.service.mapper;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponseDto;
import com.cinema.service.MapperToDto;
import com.cinema.service.MapperToEntity;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper implements MapperToDto<CinemaHall, CinemaHallResponseDto>,
        MapperToEntity<CinemaHall, CinemaHallRequestDto> {
    @Override
    public CinemaHallResponseDto mapToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        return cinemaHallResponseDto;
    }

    @Override
    public CinemaHall mapToEntity(CinemaHallRequestDto requestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(requestDto.getDescription());
        cinemaHall.setCapacity(requestDto.getCapacity());
        return cinemaHall;
    }
}
