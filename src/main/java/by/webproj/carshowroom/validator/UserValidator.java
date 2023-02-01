package by.webproj.carshowroom.validator;

import by.webproj.carshowroom.entity.User;

public interface UserValidator {

    boolean validateUserDataByLoginAndPasswordWithSecretKey(String login, String password, String secretKey);

    boolean validateUserDataByLoginAndPassword(String login, String password);
}
