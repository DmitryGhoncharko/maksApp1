package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.SalonEntity;
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
public class SalonCarDetailsDao implements CarDetailsDao<SalonEntity>{
    private static final String SQL_ADD_ENGINE = "insert into salon(salon_name,salon_weight) values(?,?)";
    private static final String SQL_FIND_ENGINE_BY_ID = "select salon_name, salon_weight from salon where salon_id = ?";
    private static final String SQL_FIND_ENGINE_BY_NAME = "select salon_id, salon_weight from salon where salon_name = ?";
    private static final String SQL_FIND_ALL_ENGINES = "select salon_id, salon_name, salon_weight from salon";
    private static final String SQL_DELETE_ENGINE_BY_ID = "delete from salon where salon_id = ?";
    private static final String SQL_UPDATE_ENGINE_BY_ID = "update salon set salon_name = ?, salon_weight = ? where salon_id = ?";
    private final ConnectionPool connectionPool;

    @Override
    public SalonEntity add(String salonName, double salonWeight) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ENGINE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, salonName);
            preparedStatement.setDouble(2, salonWeight);
            int countRowsadded = preparedStatement.executeUpdate();
            if (countRowsadded > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return new SalonEntity.Builder().withId(resultSet.getLong(1)).withName(salonName).withWeight(salonWeight).build();
                }
            }
        } catch (SQLException e) {
            log.error("Cannot add Salon, Salon name = " + salonName + " Salon weight  = " + salonWeight, e);
            throw new DaoException("Cannot add Salon, Salon name = " + salonName + " Salon weight  = " + salonWeight, e);
        }
        log.error("Cannot add Salon, Salon name = " + salonName + " Salon weight  = " + salonWeight);
        throw new DaoException("Cannot add Salon, Salon name = " + salonName + " Salon weight  = " + salonWeight);
    }

    @Override
    public Optional<SalonEntity> findById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new SalonEntity.Builder().withId(id).withName(resultSet.getString(1)).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find Salon by id, id = " + id, e);
            throw new DaoException("Cannot find Salon by id, id = " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<SalonEntity> findByName(String salonName) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_NAME)) {
            preparedStatement.setString(1, salonName);
            int countRowsFind = preparedStatement.executeUpdate();

            if (countRowsFind > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return Optional.of(new SalonEntity.Builder().withId(resultSet.getLong(1)).withName(salonName).withWeight(resultSet.getDouble(2)).build());
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find Salon by name, name = " + salonName, e);
            throw new DaoException("Cannot find Salon by name, name = " + salonName, e);
        }
        return Optional.empty();
    }

    @Override
    public List<SalonEntity> findAll() throws DaoException {
        List<SalonEntity> salonEntities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ENGINES);
            while (resultSet.next()) {
                final SalonEntity salon = new SalonEntity.Builder().withId(resultSet.getLong(1)).withName(resultSet.getString(2)).withWeight(resultSet.getDouble(3)).build();
                salonEntities.add(salon);
            }
        } catch (SQLException e) {
            log.error("Cannot find all Salon", e);
            throw new DaoException("Cannot find all Salon", e);
        }
        return salonEntities;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ENGINE_BY_ID)) {
            preparedStatement.setLong(1, id);
            int countRowsDeleted = preparedStatement.executeUpdate();
            return countRowsDeleted > 0;
        } catch (SQLException e) {
            log.error("Cannot delete Salon by id, id = " + id, e);
            throw new DaoException("Cannot delete Salon by id, id = " + id, e);
        }
    }

    @Override
    public SalonEntity update(String salonName, double salonWeight, Long salonId) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ENGINE_BY_ID)) {
            int countRowsUpdated = preparedStatement.executeUpdate();
            if (countRowsUpdated > 0) {
                return new SalonEntity.Builder().withId(salonId).withName(salonName).withWeight(salonWeight).build();
            }
        } catch (SQLException e) {
            log.error("Cannot update SuspensionEntity, Salon name = " + salonName + " Salon weight = " + salonWeight + "Salon id = " + salonId, e);
            throw new DaoException("Cannot update Suspension, Salon name = " + salonName + " Salon weight = " + salonWeight + "Salon id = " + salonId, e);
        }
        throw new DaoException("Cannot update Suspension, Salon name = " + salonName + " Salon weight = " + salonWeight + "Salon id = " + salonId);
    }
}
