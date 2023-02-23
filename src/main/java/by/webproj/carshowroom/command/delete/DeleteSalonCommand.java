package by.webproj.carshowroom.command.delete;

import by.webproj.carshowroom.command.Command;
import by.webproj.carshowroom.command.CommandRequest;
import by.webproj.carshowroom.command.CommandResponse;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.SalonEntity;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteSalonCommand implements Command {
    private final RequestFactory requestFactory;
    private final CarDetailsService<SalonEntity> salonEntityCarDetailsService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String additionId = request.getParameter("detailId");
        salonEntityCarDetailsService.deleteById(Long.parseLong(additionId));
        return requestFactory.createRedirectResponse("/controller?command=allDetails");
    }
}
