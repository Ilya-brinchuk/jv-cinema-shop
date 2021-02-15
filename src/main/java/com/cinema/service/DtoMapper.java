package com.cinema.service;

public interface DtoMapper<T, E, Q> {
    T mapToDto(E entity);

    E mapToEntity(Q requestDto);
}
