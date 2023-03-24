package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClearSessionCommand implements Command{
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        request.removeFromSession("engines");
        request.removeFromSession("suspensions");
        request.removeFromSession("bodies");
        request.removeFromSession("salons");
        request.removeFromSession("separaters");
        request.removeFromSession("additions");
        return requestFactory.createRedirectResponse("/controller?command=task");
    }
}
