package by.webproj.carshowroom.command;


import by.webproj.carshowroom.exception.ServiceError;

public interface Command {

    CommandResponse execute(CommandRequest request) throws ServiceError;

}
