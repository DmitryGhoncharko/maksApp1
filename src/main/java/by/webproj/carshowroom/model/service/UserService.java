package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.UserEntity;

import java.util.Optional;

public interface UserService {


    Optional<UserEntity> loginUser(String login, String password);

   boolean registrationUser(String login, String password);


}
