package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
public class ShowRegistrationPageCommand implements Command{
    private final RequestFactory requestFactory;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.REGISTRATION_PAGE.getPath());
    }
}
