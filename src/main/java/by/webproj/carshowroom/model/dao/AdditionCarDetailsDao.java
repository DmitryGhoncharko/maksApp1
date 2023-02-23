package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.AdditionEntity;
import by.webproj.carshowroom.entity.SuspensionEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Slf4j
public class AdditionCarDetailsDao implements CarDetailsDao<AdditionEntity> {
    private static final String SQL_ADD_ENGINE = "insert into adition(adition_name,adition_weight) values(?,?)";
    private static final String SQL_FIND_ENGINE_BY_ID = "select adition_name, adition_weight from adition where adition_id = ?";
    private static final String SQL_FIND_ENGINE_BY_NAME = "select adition_id, adition_weight from adition where adition_name = ?";
    private static final String SQL_FIND_ALL_ENGINES = "select adition_id, adition_name, adition_weight from adition";
    private static final String SQL_DELETE_ENGINE_BY_ID = "delete from adition where adition_id = ?";
    private static final String SQL_UPDATE_ENGINE_BY_ID = "update adition set adition_name = ?, adition_weight = ? where adition_id = ?";
    private final ConnectionPool connectionPool;

    @Override
    public AdditionEntity add(String suspensionName, double suspensionWeight) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ENGINE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, suspensionName);
            preparedStatement.setDouble(2, suspensionWeight);
            int countRowsadded = preparedStatement.executeUpdate();
            if (countRowsadded > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return new AdditionEntity.Builder().withId(resultSet.getLong(1)).withName(suspensionName).withWeight(suspensionWeight).build();
                }
            }
        } catch (SQLException e) {
            log.error("Cannot add AdditionEntity, AdditionEntity name = " + suspensionName + " AdditionEntity weight  = " + suspensionWeight, e);
            throw new DaoException("Cannot add AdditionEntity, AdditionEntity name = " + suspensionName + " AdditionEntity weight  = " + suspensionWeight, e);
        }
        log.error("Cannot add AdditionEntity, AdditionEntity name = " + suspensionName + " AdditionEntity weight  = " + suspensionWeight);
        throw new DaoException("Cannot add AdditionEntity, AdditionEntity name = " + suspensionName + " AdditionEntity weight  = " + suspensionWeight);
    }

    @Override
    public Optional<AdditionEntity> findById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new AdditionEntity.Builder().withId(id).withName(resultSet.getString(1)).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find AdditionEntity by id, id = " + id, e);
            throw new DaoException("Cannot find AdditionEntity by id, id = " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<AdditionEntity> findByName(String suspensionName) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_NAME)) {
            preparedStatement.setString(1, suspensionName);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new AdditionEntity.Builder().withId(resultSet.getLong(1)).withName(suspensionName).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find AdditionEntity by name, name = " + suspensionName, e);
            throw new DaoException("Cannot find AdditionEntity by name, name = " + suspensionName, e);
        }
        return Optional.empty();
    }

    @Override
    public List<AdditionEntity> findAll() throws DaoException {
        List<AdditionEntity> suspensionEntities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ENGINES);
            while (resultSet.next()) {
                final AdditionEntity addition = new AdditionEntity.Builder().withId(resultSet.getLong(1)).withName(resultSet.getString(2)).withWeight(resultSet.getDouble(3)).build();
                suspensionEntities.add(addition);
            }
        } catch (SQLException e) {
            log.error("Cannot find all AdditionEntity", e);
            throw new DaoException("Cannot find all AdditionEntity", e);
        }
        return suspensionEntities;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsDeleted = preparedStatement.executeUpdate();
            return countRowsDeleted > 0;
        } catch (SQLException e) {
            log.error("Cannot delete AdditionEntity by id, id = " + id, e);
            throw new DaoException("Cannot delete AdditionEntity by id, id = " + id, e);
        }
    }

    @Override
    public AdditionEntity update(String suspensionName, double suspensionWeight, Long suspensionId) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ENGINE_BY_ID)) {
            int countRowsUpdated = preparedStatement.executeUpdate();
            if (countRowsUpdated > 0) {
                return new AdditionEntity.Builder().withId(suspensionId).withName(suspensionName).withWeight(suspensionWeight).build();
            }
        } catch (SQLException e) {
            log.error("Cannot update AdditionEntity, AdditionEntity = " + suspensionName + " AdditionEntity weight = " + suspensionWeight + "AdditionEntity id = " + suspensionId, e);
            throw new DaoException("Cannot update AdditionEntity, AdditionEntity = " + suspensionName + " AdditionEntity weight = " + suspensionWeight + "AdditionEntity id = " + suspensionId, e);
        }
        throw new DaoException("Cannot update AdditionEntity, AdditionEntity = " + suspensionName + " AdditionEntity weight = " + suspensionWeight + "AdditionEntity id = " + suspensionId);
    }
}
