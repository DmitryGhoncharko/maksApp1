package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

   Optional<User> findUserByLogin(String login) throws DaoException;

}
