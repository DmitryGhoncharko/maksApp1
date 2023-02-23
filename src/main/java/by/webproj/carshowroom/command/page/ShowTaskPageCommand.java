package by.webproj.carshowroom.command.page;

import by.webproj.carshowroom.command.Command;
import by.webproj.carshowroom.command.CommandRequest;
import by.webproj.carshowroom.command.CommandResponse;
import by.webproj.carshowroom.command.PagePath;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowTaskPageCommand implements Command {
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.TASK_PAGE.getPath());
    }
}
