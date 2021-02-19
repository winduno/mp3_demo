package com.example.demo.service;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> getAll();

    T save(T t);

    Optional<T> findById(Long id);

    void delete(Long id);
}
