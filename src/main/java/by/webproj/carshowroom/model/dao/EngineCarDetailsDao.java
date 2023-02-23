package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.EngineEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class EngineCarDetailsDao implements CarDetailsDao<EngineEntity> {
    private static final String SQL_ADD_ENGINE = "insert into carengine(carengine_name,carengine_weight) values(?,?)";

    private static final String SQL_FIND_ENGINE_BY_ID = "select carengine_name, carengine_weight from carengine where carengine_id = ?";
    private static final String SQL_FIND_ENGINE_BY_NAME = "select carengine_id, carengine_weight from carengine where carengine_name = ?";

    private static final String SQL_FIND_ALL_ENGINES = "select carengine_id, carengine_name, carengine_weight from carengine";
    private static final String SQL_DELETE_ENGINE_BY_ID = "delete from carengine where carengine_id = ?";

    private static final String SQL_UPDATE_ENGINE_BY_ID = "update carengine set carengine_name = ?, cerengine_weight = ? where carengine_id = ?";
    private final ConnectionPool connectionPool;

    @Override
    public EngineEntity add(String engineName, double engineWeight) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ENGINE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, engineName);
            preparedStatement.setDouble(2, engineWeight);
            int countRowsadded = preparedStatement.executeUpdate();
            if (countRowsadded > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return new EngineEntity.Builder().withId(resultSet.getLong(1)).withName(engineName).withWeight(engineWeight).build();
                }
            }
        } catch (SQLException e) {
            log.error("Cannot add engine, engine name = " + engineName + " engine weight  = " + engineWeight, e);
            throw new DaoException("Cannot add engine, engine name = " + engineName + " engine weight  = " + engineWeight, e);
        }
        log.error("Cannot add engine, engine name = " + engineName + " engine weight  = " + engineWeight);
        throw new DaoException("Cannot add engine, engine name = " + engineName + " engine weight  = " + engineWeight);
    }

    @Override
    public Optional<EngineEntity> findById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new EngineEntity.Builder().withId(id).withName(resultSet.getString(1)).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find carengine by id, id = " + id, e);
            throw new DaoException("Cannot find carengine by id, id = " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<EngineEntity> findByName(String engineName) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_NAME)) {
            preparedStatement.setString(1, engineName);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new EngineEntity.Builder().withId(resultSet.getLong(1)).withName(engineName).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find carengine by name, name = " + engineName, e);
            throw new DaoException("Cannot find carengine by name, name = " + engineName, e);
        }
        return Optional.empty();
    }

    @Override
    public List<EngineEntity> findAll() throws DaoException {
        List<EngineEntity> engineEntities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ENGINES);
            while (resultSet.next()) {
                final EngineEntity engine = new EngineEntity.Builder().withId(resultSet.getLong(1)).withName(resultSet.getString(2)).withWeight(resultSet.getDouble(3)).build();
                engineEntities.add(engine);
            }
        } catch (SQLException e) {
            log.error("Cannot find all engines", e);
            throw new DaoException("Cannot find all engines", e);
        }
        return engineEntities;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsDeleted = preparedStatement.executeUpdate();
            return countRowsDeleted > 0;
        } catch (SQLException e) {
            log.error("Cannot delete engine by id, id = " + id, e);
            throw new DaoException("Cannot delete engine by id, id = " + id, e);
        }
    }

    @Override
    public EngineEntity update(String engineName, double engineWeight, Long engineId) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ENGINE_BY_ID)) {
            int countRowsUpdated = preparedStatement.executeUpdate();
            if (countRowsUpdated > 0) {
                return new EngineEntity.Builder().withId(engineId).withName(engineName).withWeight(engineWeight).build();
            }
        } catch (SQLException e) {
            log.error("Cannot update carengine, engineName = " + engineName + " engine weight = " + engineWeight + "engine id = " + engineId, e);
            throw new DaoException("Cannot update carengine, engineName = " + engineName + " engine weight = " + engineWeight + "engine id = " + engineId, e);
        }
        throw new DaoException("Cannot update carengine, engineName = " + engineName + " engine weight = " + engineWeight + "engine id = " + engineId);
    }
}
