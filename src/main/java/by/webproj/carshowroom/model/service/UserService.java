package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.User;

import java.util.Optional;

public interface UserService {


    Optional<User> loginUser(String login, String password);

    Optional<User> registrationUser(String login, String password);


}
