package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    Optional<User> authenticateIfAdmin(String login, String password);

    Optional<User> authenticateIfClient(String login, String password);


    List<User> findAllClients();
}
