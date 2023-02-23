package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CarDetailsService <T>{
    T add(String name, double weight);

    Optional<T> findById(Long id);

    Optional<T> findByName(String name);

    List<T> findAll();

    boolean deleteById(Long id);

    T updateUpdate(String name, double weight, Long id);
}
