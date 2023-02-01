package by.webproj.carshowroom.command;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();

}