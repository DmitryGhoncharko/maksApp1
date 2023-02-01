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
public class SimpleEngineDao implements EngineDao {
    private static final String SQL_ADD_ENGINE  = "insert into carengine(carengine_name,carengine_weight) values(?,?)";

    private static final String SQL_FIND_ENGINE_BY_ID  = "select carengine_name, carengine_weight from carengine where carengine_id = ?";
    private static final String SQL_FIND_ENGINE_BY_NAME  = "select carengine_id, carengine_weight from carengine where carengine_name = ?";

    private static final String SQL_FIND_ALL_ENGINES  = "select carengine_id, carengine_name, carengine_weight from carengine";

    private final ConnectionPool connectionPool;

    @Override
    public EngineEntity addEngine(String engineName, double engineWeight) throws DaoException {
        try(final Connection connection =  connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ENGINE,Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,engineName);
            preparedStatement.setDouble(2,engineWeight);
            int countRowsadded = preparedStatement.executeUpdate();
            if(countRowsadded>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    return  new EngineEntity.Builder().
                            withId(resultSet.getLong(1)).
                            withEngineName(engineName).
                            withEngineWeight(engineWeight).
                            build();
                }
            }
        }catch (SQLException e ){
            log.error("Cannot add engine, engine name = "  + engineName  +  " engine weight  = "  + engineWeight,e);
            throw new DaoException("Cannot add engine, engine name = "  + engineName  +  " engine weight  = "  + engineWeight,e);
        }
        log.error("Cannot add engine, engine name = "  + engineName  +  " engine weight  = "  + engineWeight);
        throw new DaoException("Cannot add engine, engine name = "  + engineName  +  " engine weight  = "  + engineWeight);
    }

    @Override
    public Optional<EngineEntity> findEngineById(Long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_ID)){
            preparedStatement.setLong(1,id);
            int countRowsFind = preparedStatement.executeUpdate();

            if(countRowsFind>0){
                ResultSet resultSet = preparedStatement.getResultSet();
                if(resultSet.next()){
                    return  Optional.of(new EngineEntity.Builder().
                            withId(id).
                            withEngineName(resultSet.getString(1)).
                            withEngineWeight(resultSet.getDouble(2)).
                            build());
                }
            }
        }catch (SQLException e ){
            log.error("Cannot find carengine by id, id = "  + id,e);
            throw new DaoException("Cannot find carengine by id, id = "  + id,e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<EngineEntity> findEngineByName(String engineName) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ENGINE_BY_NAME)){
            preparedStatement.setString(1,engineName);
            int countRowsFind = preparedStatement.executeUpdate();

            if(countRowsFind>0){
                ResultSet resultSet = preparedStatement.getResultSet();
                if(resultSet.next()){
                    return  Optional.of(new EngineEntity.Builder().
                            withId(resultSet.getLong(1)).
                            withEngineName(engineName).
                            withEngineWeight(resultSet.getDouble(2)).
                            build());
                }
            }
        }catch (SQLException e ){
            log.error("Cannot find carengine by name, name = "  + engineName,e);
            throw new DaoException("Cannot find carengine by name, name = "  + engineName,e);
        }
        return Optional.empty();
    }

    @Override
    public List<EngineEntity> findAllEngines() throws DaoException {
        List<EngineEntity> engineEntities =  new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet  = statement.executeQuery(SQL_FIND_ALL_ENGINES);
            while (resultSet.next()){
                final EngineEntity engine = new EngineEntity.Builder().
                        withId(resultSet.getLong(1)).
                        withEngineName(resultSet.getString(2)).
                        withEngineWeight(resultSet.getDouble(3)).
                        build();
                engineEntities.add(engine);
            }
        }catch (SQLException e ){

        }
        return engineEntities;
    }

    @Override
    public boolean deleteEngineById() throws DaoException {
        return false;
    }

    @Override
    public EngineEntity updateEngine(String engineName, double engineWeight) {
        return null;
    }
}
