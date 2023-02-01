package by.webproj.carshowroom.command;

public interface ServiceLocator {
    Command getCommand(String commandName);
}
