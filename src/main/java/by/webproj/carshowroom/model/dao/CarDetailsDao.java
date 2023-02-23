package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CarDetailsDao <T>{
    T add(String name, double weight) throws DaoException;

    Optional<T> findById(Long id) throws DaoException;

    Optional<T> findByName(String name) throws DaoException;

    List<T> findAll() throws DaoException;

    boolean deleteById(Long id) throws DaoException;

    T update(String name, double weight, Long id) throws DaoException;
}
