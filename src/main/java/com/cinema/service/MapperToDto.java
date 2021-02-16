package com.cinema.service;

public interface MapperToDto<E, T> {
    T mapToDto(E entity);
}
