package com.cinema.controller;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponseDto;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MapperToDto;
import com.cinema.service.MapperToEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final MapperToDto<CinemaHall, CinemaHallResponseDto> mapperToDto;
    private final MapperToEntity<CinemaHall, CinemaHallRequestDto> mapperToEntity;
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(MapperToDto<CinemaHall, CinemaHallResponseDto> mapperToDto,
                                MapperToEntity<CinemaHall, CinemaHallRequestDto> mapToEntity,
                                CinemaHallService cinemaHallService) {
        this.mapperToDto = mapperToDto;
        this.mapperToEntity = mapToEntity;
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(mapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody @Valid CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(mapperToEntity.mapToEntity(cinemaHallRequestDto));
    }
}
