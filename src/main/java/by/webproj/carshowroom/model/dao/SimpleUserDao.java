package by.webproj.carshowroom.model.dao;


import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class SimpleUserDao implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserDao.class);
    private static final String SQL_FIND_USER_BY_LOGIN = "select user_id, user_login, user_password  from  user " + "where user_login = ?";

    private static final String SQL_ADD_USER = "insert into user(user_login, user_password) values(?,?)";
    private final ConnectionPool connectionPool;


    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User.Builder().withUserId(resultSet.getLong(1)).withUserLogin(resultSet.getString(2)).withUserPassword(resultSet.getString(3)).build());
            }
        } catch (SQLException sqlException) {
            LOG.error("Cannot find user by login, login: " + login, sqlException);
            throw new DaoException("Cannot find user by login, login: " + login, sqlException);
        }
        LOG.error("Cannot find user by login, login: " + login);
        return Optional.empty();
    }

    @Override
    public boolean addUser(String login, String password) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOG.error("Cannot add user userLogin = " + login + " userPassord =  " + password);
            throw new DaoException("Cannot add user userLogin = " + login + " userPassord =  " + password);
        }
    }
}
