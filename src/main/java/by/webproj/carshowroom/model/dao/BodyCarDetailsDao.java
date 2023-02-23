package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.BodyEntity;
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
public class BodyCarDetailsDao implements CarDetailsDao<BodyEntity> {
    private static final String SQL_ADD_ENGINE = "insert into body(body_name,body_weight) values(?,?)";
    private static final String SQL_FIND_ENGINE_BY_ID = "select body_name, body_weight from body where body_id = ?";
    private static final String SQL_FIND_ENGINE_BY_NAME = "select body_id, body_weight from body where body_name = ?";
    private static final String SQL_FIND_ALL_ENGINES = "select body_id, body_name, body_weight from body";
    private static final String SQL_DELETE_ENGINE_BY_ID = "delete from body where body_id = ?";
    private static final String SQL_UPDATE_ENGINE_BY_ID = "update body set body_name = ?, body_weight = ? where body_id = ?";

    private final ConnectionPool connectionPool;

    @Override
    public BodyEntity add(String bodyName, double bodyWeight) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ENGINE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bodyName);
            preparedStatement.setDouble(2, bodyWeight);
            int countRowsadded = preparedStatement.executeUpdate();
            if (countRowsadded > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return new BodyEntity.Builder().withId(resultSet.getLong(1)).withName(bodyName).withWeight(bodyWeight).build();
                }
            }
        } catch (SQLException e) {
            log.error("Cannot add body, body name = " + bodyName + " body weight  = " + bodyWeight, e);
            throw new DaoException("Cannot add body, body name = " + bodyName + " body weight  = " + bodyWeight, e);
        }
        log.error("Cannot add Suspension, body name = " + bodyName + " body weight  = " + bodyWeight);
        throw new DaoException("Cannot add body, body name = " + bodyName + " body weight  = " + bodyWeight);
    }

    @Override
    public Optional<BodyEntity> findById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new BodyEntity.Builder().withId(id).withName(resultSet.getString(1)).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find body by id, id = " + id, e);
            throw new DaoException("Cannot find body by id, id = " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<BodyEntity> findByName(String bodyName) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_NAME)) {
            preparedStatement.setString(1, bodyName);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new BodyEntity.Builder().withId(resultSet.getLong(1)).withName(bodyName).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find body by name, name = " + bodyName, e);
            throw new DaoException("Cannot find body by name, name = " + bodyName, e);
        }
        return Optional.empty();
    }

    @Override
    public List<BodyEntity> findAll() throws DaoException {
        List<BodyEntity> bodyEntities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ENGINES);
            while (resultSet.next()) {
                final BodyEntity body = new BodyEntity.Builder().withId(resultSet.getLong(1)).withName(resultSet.getString(2)).withWeight(resultSet.getDouble(3)).build();
                bodyEntities.add(body);
            }
        } catch (SQLException e) {
            log.error("Cannot find all bodies", e);
            throw new DaoException("Cannot find all bodies", e);
        }
        return bodyEntities;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsDeleted = preparedStatement.executeUpdate();
            return countRowsDeleted > 0;
        } catch (SQLException e) {
            log.error("Cannot delete body by id, id = " + id, e);
            throw new DaoException("Cannot delete body by id, id = " + id, e);
        }
    }

    @Override
    public BodyEntity update(String bodyName, double bodyWeight, Long bodyId) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ENGINE_BY_ID)) {
            int countRowsUpdated = preparedStatement.executeUpdate();
            if (countRowsUpdated > 0) {
                return new BodyEntity.Builder().withId(bodyId).withName(bodyName).withWeight(bodyWeight).build();
            }
        } catch (SQLException e) {
            log.error("Cannot update SuspensionEntity, SuspensionName = " + bodyName + " Suspension weight = " + bodyWeight + "Suspension id = " + bodyId, e);
            throw new DaoException("Cannot update Suspension, SuspensionName = " + bodyName + " Suspension weight = " + bodyWeight + "Suspension id = " + bodyId, e);
        }
        throw new DaoException("Cannot update Suspension, SuspensionName = " + bodyName + " Suspension weight = " + bodyWeight + "Suspension id = " + bodyId);
    }
}
