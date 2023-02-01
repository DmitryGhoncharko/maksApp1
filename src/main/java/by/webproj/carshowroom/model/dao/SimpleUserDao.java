package by.webproj.carshowroom.model.dao;


import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleUserDao implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserDao.class);
    private static final String SQL_ADD_USER = "insert into user(user_login, user_password, user_role_id) values (?,?,?)";
    private static final String SQL_FIND_USER_BY_LOGIN = "select user_id, user_login, user_password, r.role_name  from  user " +
            "left join role r on r.role_id = user.user_role_id " +
            "where user_login = ?";
    private static final String SQL_FIND_ALL_CLIENTS  = "select user_id, user_login, user_password, r.role_name  from  user" +
            " left join role r on user.user_role_id = r.role_id";
    private final ConnectionPool connectionPool;

    public SimpleUserDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User.Builder().
                        withUserId(resultSet.getLong(1)).
                        withUserLogin(resultSet.getString(2)).
                        withUserPassword(resultSet.getString(3)).
                        build());
            }
        } catch (SQLException sqlException) {
            LOG.error("Cannot find user by login, login: " + login, sqlException);
            throw new DaoException("Cannot find user by login, login: " + login, sqlException);
        }
        LOG.error("Cannot find user by login, login: " + login);
        return Optional.empty();
    }


}
