package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutCommand implements Command {
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private final RequestFactory requestFactory;

    public LogoutCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (noLoggedInUserPresent(request)) {
            return requestFactory.createForwardResponse(PagePath.MAIN_PAGE.getPath());
        }
        request.clearSession();
        return requestFactory.createRedirectResponse(PagePath.INDEX_PATH.getPath());
    }

    private boolean noLoggedInUserPresent(CommandRequest request) {
        return !request.sessionExists() || (
                request.sessionExists()
                        && !request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME)
                        .isPresent()
        );
    }
}
