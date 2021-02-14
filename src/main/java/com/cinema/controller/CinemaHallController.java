package com.cinema.controller;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponseDto;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MapperDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final MapperDto<CinemaHallResponseDto, CinemaHall, CinemaHallRequestDto> mapperDto;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService,
                                MapperDto<CinemaHallResponseDto, CinemaHall,
                                        CinemaHallRequestDto> mapperDto) {
        this.cinemaHallService = cinemaHallService;
        this.mapperDto = mapperDto;
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(mapperDto::replaceWithDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(mapperDto.replaceWithEntity(cinemaHallRequestDto));
    }
}
