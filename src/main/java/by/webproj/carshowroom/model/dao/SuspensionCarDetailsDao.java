package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.SuspensionEntity;
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
public class SuspensionCarDetailsDao implements CarDetailsDao<SuspensionEntity> {
    private static final String SQL_ADD_ENGINE = "insert into suspension(suspension_name,suspension_weight) values(?,?)";
    private static final String SQL_FIND_ENGINE_BY_ID = "select suspension_name, suspension_weight from suspension where suspension_id = ?";
    private static final String SQL_FIND_ENGINE_BY_NAME = "select suspension_id, suspension_weight from suspension where suspension_name = ?";
    private static final String SQL_FIND_ALL_ENGINES = "select suspension_id, suspension_name, suspension_weight from suspension";
    private static final String SQL_DELETE_ENGINE_BY_ID = "delete from suspension where suspension_id = ?";
    private static final String SQL_UPDATE_ENGINE_BY_ID = "update suspension set suspension_name = ?, suspension_weight = ? where suspension_id = ?";
    private final ConnectionPool connectionPool;

    @Override
    public SuspensionEntity add(String suspensionName, double suspensionWeight) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ENGINE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, suspensionName);
            preparedStatement.setDouble(2, suspensionWeight);
            int countRowsadded = preparedStatement.executeUpdate();
            if (countRowsadded > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return new SuspensionEntity.Builder().withId(resultSet.getLong(1)).withName(suspensionName).withWeight(suspensionWeight).build();
                }
            }
        } catch (SQLException e) {
            log.error("Cannot add Suspension, Suspension name = " + suspensionName + " Suspension weight  = " + suspensionWeight, e);
            throw new DaoException("Cannot add Suspension, Suspension name = " + suspensionName + " Suspension weight  = " + suspensionWeight, e);
        }
        log.error("Cannot add Suspension, Suspension name = " + suspensionName + " Suspension weight  = " + suspensionWeight);
        throw new DaoException("Cannot add Suspension, Suspension name = " + suspensionName + " Suspension weight  = " + suspensionWeight);
    }

    @Override
    public Optional<SuspensionEntity> findById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new SuspensionEntity.Builder().withId(id).withName(resultSet.getString(1)).withWeight(resultSet.getDouble(2)).build());
            }
        } catch (SQLException e) {
            log.error("Cannot find Suspension by id, id = " + id, e);
            throw new DaoException("Cannot find Suspension by id, id = " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<SuspensionEntity> findByName(String suspensionName) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_NAME)) {
            preparedStatement.setString(1, suspensionName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new SuspensionEntity.Builder().withId(resultSet.getLong(1)).withName(suspensionName).withWeight(resultSet.getDouble(2)).build());
            }
        } catch (SQLException e) {
            log.error("Cannot find Suspension by name, name = " + suspensionName, e);
            throw new DaoException("Cannot find Suspension by name, name = " + suspensionName, e);
        }
        return Optional.empty();
    }

    @Override
    public List<SuspensionEntity> findAll() throws DaoException {
        List<SuspensionEntity> suspensionEntities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ENGINES);
            while (resultSet.next()) {
                final SuspensionEntity suspension = new SuspensionEntity.Builder().withId(resultSet.getLong(1)).withName(resultSet.getString(2)).withWeight(resultSet.getDouble(3)).build();
                suspensionEntities.add(suspension);
            }
        } catch (SQLException e) {
            log.error("Cannot find all Suspensions", e);
            throw new DaoException("Cannot find all Suspensions", e);
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
            log.error("Cannot delete Suspension by id, id = " + id, e);
            throw new DaoException("Cannot delete Suspension by id, id = " + id, e);
        }
    }

    @Override
    public SuspensionEntity update(String suspensionName, double suspensionWeight, Long suspensionId) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ENGINE_BY_ID)) {
            preparedStatement.setString(1, suspensionName);
            preparedStatement.setDouble(2, suspensionWeight);
            preparedStatement.setLong(3, suspensionId);
            int countRowsUpdated = preparedStatement.executeUpdate();
            if (countRowsUpdated > 0) {
                return new SuspensionEntity.Builder().withId(suspensionId).withName(suspensionName).withWeight(suspensionWeight).build();
            }
        } catch (SQLException e) {
            log.error("Cannot update SuspensionEntity, SuspensionName = " + suspensionName + " Suspension weight = " + suspensionWeight + "Suspension id = " + suspensionId, e);
            throw new DaoException("Cannot update Suspension, SuspensionName = " + suspensionName + " Suspension weight = " + suspensionWeight + "Suspension id = " + suspensionId, e);
        }
        throw new DaoException("Cannot update Suspension, SuspensionName = " + suspensionName + " Suspension weight = " + suspensionWeight + "Suspension id = " + suspensionId);
    }
}
