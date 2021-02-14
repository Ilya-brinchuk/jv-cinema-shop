package com.cinema.service;

public interface MapperDto<T,E, Q> {
    T replaceWithDto(E entity);

    E replaceWithEntity(Q requestDto);
}
