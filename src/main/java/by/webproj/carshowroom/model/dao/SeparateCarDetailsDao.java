package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.SeparaterlyEntity;
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
public class SeparateCarDetailsDao implements CarDetailsDao<SeparaterlyEntity>{
    private static final String SQL_ADD_ENGINE = "insert into separate(separate_name,separate_weight) values(?,?)";
    private static final String SQL_FIND_ENGINE_BY_ID = "select separate_name, separate_weight from separate where separate_id = ?";
    private static final String SQL_FIND_ENGINE_BY_NAME = "select separate_id, separate_weight from separate where separate_name = ?";
    private static final String SQL_FIND_ALL_ENGINES = "select separate_id, separate_name, separate_weight from separate";
    private static final String SQL_DELETE_ENGINE_BY_ID = "delete from separate where separate_id = ?";
    private static final String SQL_UPDATE_ENGINE_BY_ID = "update separate set separate_name = ?, separate_weight = ? where separate_id = ?";
    private final ConnectionPool connectionPool;

    @Override
    public SeparaterlyEntity add(String sepName, double sepWeight) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ENGINE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, sepName);
            preparedStatement.setDouble(2, sepWeight);
            int countRowsadded = preparedStatement.executeUpdate();
            if (countRowsadded > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return new SeparaterlyEntity.Builder().withId(resultSet.getLong(1)).withName(sepName).withWeight(sepWeight).build();
                }
            }
        } catch (SQLException e) {
            log.error("Cannot add Suspension, sep name = " + sepName + " sep weight  = " + sepWeight, e);
            throw new DaoException("Cannot add sep, sep name = " + sepName + " sep weight  = " + sepWeight, e);
        }
        log.error("Cannot add sep, sep name = " + sepName + " sep weight  = " + sepWeight);
        throw new DaoException("Cannot add sep, sep name = " + sepName + " sep weight  = " + sepWeight);
    }

    @Override
    public Optional<SeparaterlyEntity> findById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new SeparaterlyEntity.Builder().withId(id).withName(resultSet.getString(1)).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find sep by id, id = " + id, e);
            throw new DaoException("Cannot find sep by id, id = " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<SeparaterlyEntity> findByName(String sepName) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_NAME)) {
            preparedStatement.setString(1, sepName);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new SeparaterlyEntity.Builder().withId(resultSet.getLong(1)).withName(sepName).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find sep by name, name = " + sepName, e);
            throw new DaoException("Cannot find sep by name, name = " + sepName, e);
        }
        return Optional.empty();
    }

    @Override
    public List<SeparaterlyEntity> findAll() throws DaoException {
        List<SeparaterlyEntity> suspensionEntities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ENGINES);
            while (resultSet.next()) {
                final SeparaterlyEntity suspension = new SeparaterlyEntity.Builder().withId(resultSet.getLong(1)).withName(resultSet.getString(2)).withWeight(resultSet.getDouble(3)).build();
                suspensionEntities.add(suspension);
            }
        } catch (SQLException e) {
            log.error("Cannot find all sep", e);
            throw new DaoException("Cannot find all sep", e);
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
            log.error("Cannot delete sep by id, id = " + id, e);
            throw new DaoException("Cannot delete sep by id, id = " + id, e);
        }
    }

    @Override
    public SeparaterlyEntity update(String sepName, double sepWeight, Long sepId) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ENGINE_BY_ID)) {
            int countRowsUpdated = preparedStatement.executeUpdate();
            if (countRowsUpdated > 0) {
                return new SeparaterlyEntity.Builder().withId(sepId).withName(sepName).withWeight(sepWeight).build();
            }
        } catch (SQLException e) {
            log.error("Cannot update sep, sepName = " + sepName + " sep weight = " + sepWeight + "sep id = " + sepId, e);
            throw new DaoException("Cannot update sep, sepName = " + sepName + " sep weight = " + sepWeight + "sep id = " + sepId, e);
        }
        throw new DaoException("Cannot update sep, sep = " + sepName + " sep weight = " + sepWeight + "sep id = " + sepId);
    }
}
