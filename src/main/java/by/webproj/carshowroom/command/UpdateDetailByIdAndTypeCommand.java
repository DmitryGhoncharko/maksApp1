package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateDetailByIdAndTypeCommand implements Command {
    private final RequestFactory requestFactory;
    private final CarDetailsService<AdditionEntity> additionEntityCarDetailsService;
    private final CarDetailsService<BodyEntity> bodyEntityCarDetailsService;
    private final CarDetailsService<EngineEntity> engineEntityCarDetailsService;
    private final CarDetailsService<SalonEntity> salonEntityCarDetailsService;
    private final CarDetailsService<SeparaterlyEntity> separaterlyEntityCarDetailsService;
    private final CarDetailsService<SuspensionEntity> suspensionEntityCarDetailsService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String detailName = request.getParameter("detailName");
        String detailWeight = request.getParameter("detailWeight");
        proceed(request, type, id, detailName, detailWeight);
        return requestFactory.createRedirectResponse("/controller?command=allDetails");
    }

    private void proceed(CommandRequest commandRequest, String detailType, String detailId, String detailName, String detailWeight) {
        switch (detailType) {
            case "engine": {
                proceed(engineEntityCarDetailsService, detailId, commandRequest, detailName, detailWeight);
                break;
            }
            case "susp": {
                proceed(suspensionEntityCarDetailsService, detailId, commandRequest, detailName, detailWeight);
                break;
            }
            case "body": {
                proceed(bodyEntityCarDetailsService, detailId, commandRequest, detailName, detailWeight);
                break;
            }
            case "salon": {
                proceed(salonEntityCarDetailsService, detailId, commandRequest, detailName, detailWeight);
                break;
            }
            case "sep": {
                proceed(separaterlyEntityCarDetailsService, detailId, commandRequest, detailName, detailWeight);
                break;
            }
            case "addition": {
                proceed(additionEntityCarDetailsService, detailId, commandRequest, detailName, detailWeight);
                break;
            }
        }
    }

    private void proceed(CarDetailsService<?> carDetailsService, String detailId, CommandRequest commandRequest, String detailName, String detailWeight) {
        carDetailsService.updateUpdate(detailName, Double.parseDouble(detailWeight), Long.parseLong(detailId));
    }
}
