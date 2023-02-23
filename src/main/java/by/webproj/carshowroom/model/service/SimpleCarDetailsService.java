package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CarDetailsDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class SimpleCarDetailsService<T> implements CarDetailsService<T> {
    private final CarDetailsDao<T> carDetailsDao;

    @Override
    public T add(String name, double weight) {
        try {
            return carDetailsDao.add(name, weight);
        } catch (DaoException e) {
            log.error("Cannot add " + carDetailsDao.getClass() + " name =" + name + " weight =" + weight, e);
            throw new ServiceError("Cannot add " + carDetailsDao.getClass() + " name =" + name + " weight =" + weight, e);
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        try {
            return carDetailsDao.findById(id);
        } catch (DaoException e) {
            log.error("Cannot find by id " + carDetailsDao.getClass() + " id=" + id, e);
            throw new ServiceError("Cannot find by id " + carDetailsDao.getClass() + " id=" + id, e);
        }
    }

    @Override
    public Optional<T> findByName(String name) {
        try {
            return carDetailsDao.findByName(name);
        } catch (DaoException e) {
            log.error("Cannot find by name " + carDetailsDao.getClass() + " name=" + name, e);
            throw new ServiceError("Cannot find by name " + carDetailsDao.getClass() + " name=" + name, e);
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return carDetailsDao.findAll();
        } catch (DaoException e) {
            log.error("Cannot find all " + carDetailsDao.getClass(), e);
            throw new ServiceError("Cannot find all " + carDetailsDao.getClass(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            return carDetailsDao.deleteById(id);
        } catch (DaoException e) {
            log.error("Cannot delete by id " + carDetailsDao.getClass() + " id=" + id);
            throw new ServiceError("Cannot delete by id " + carDetailsDao.getClass() + " id=" + id);
        }
    }

    @Override
    public T updateUpdate(String name, double weight, Long id) {
        try {
            return carDetailsDao.update(name, weight, id);
        } catch (DaoException e) {
            log.error("Cannot update " + carDetailsDao.getClass() + " name=" + name + " weight=" + weight + " id=" + id, e);
            throw new ServiceError("Cannot update " + carDetailsDao.getClass() + " name=" + name + " weight=" + weight + " id=" + id, e);
        }
    }
}
