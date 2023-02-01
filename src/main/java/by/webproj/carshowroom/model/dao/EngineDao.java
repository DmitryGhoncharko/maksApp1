package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.EngineEntity;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface EngineDao {

    EngineEntity addEngine(String engineName, double engineWeight) throws DaoException;

    Optional<EngineEntity> findEngineById(Long id) throws DaoException;

    Optional<EngineEntity> findEngineByName(String engineName) throws DaoException;

    List<EngineEntity> findAllEngines() throws DaoException;

    boolean deleteEngineById(Long id) throws DaoException;

    EngineEntity updateEngine(String engineName, double engineWeight, Long engineId) throws DaoException;

}

