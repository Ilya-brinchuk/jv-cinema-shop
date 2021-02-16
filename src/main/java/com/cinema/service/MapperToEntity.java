package com.cinema.service;

public interface MapperToEntity<E, T> {
    E mapToEntity(T requestDto);
}
