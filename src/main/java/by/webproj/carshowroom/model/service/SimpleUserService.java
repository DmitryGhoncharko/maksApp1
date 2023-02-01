package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;

import by.webproj.carshowroom.model.dao.UserDao;
import by.webproj.carshowroom.securiy.PasswordHasher;
import by.webproj.carshowroom.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class SimpleUserService implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserService.class);
    private final UserValidator userValidator;
    private final UserDao userDao;
    private final PasswordHasher passwordHasher;


    @Override
    public Optional<User> authenticateIfAdmin(String login, String password) {
        if (!userValidator.validateUserDataByLoginAndPassword(login, password)) {
            return Optional.empty();
        }
        try {

            final Optional<User> userFromDB = userDao.findUserByLogin(login);
            if (userFromDB.isPresent()) {
                final User userInstance = userFromDB.get();
                final String hashedPasswordFromDB = userInstance.getPassword();
                if (passwordHasher.checkIsEqualsPasswordAndPasswordHash(password, hashedPasswordFromDB)) {
                    return userFromDB;
                }
            }
        } catch (DaoException daoException) {
            LOG.error("Cannot authorize user, userLogin: " + login + " userPassword :" + password, daoException);
            throw new ServiceError("Cannot authorize user, userLogin: " + login + " userPassword :" + password);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> authenticateIfClient(String login, String password) {
        if (!userValidator.validateUserDataByLoginAndPassword(login, password)) {
            return Optional.empty();
        }
        try {

            final Optional<User> userFromDB = userDao.findUserByLogin(login);
            if (userFromDB.isPresent()) {
                final User userInstance = userFromDB.get();
                final String hashedPasswordFromDB = userInstance.getPassword();
                if (passwordHasher.checkIsEqualsPasswordAndPasswordHash(password, hashedPasswordFromDB)) {
                    return userFromDB;
                }
            }
        } catch (DaoException daoException) {
            LOG.error("Cannot authorize user, userLogin: " + login + " userPassword :" + password, daoException);
            throw new ServiceError("Cannot authorize user, userLogin: " + login + " userPassword :" + password);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllClients() {
        try{
            return userDao.findAllClients();
        }catch (DaoException e){
            LOG.error("Cannot find users as clients",e);
            throw new ServiceError("Cannot find users as clients",e);
        }
    }
}
