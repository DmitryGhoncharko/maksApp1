package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetDetailByTypeIdCommand implements Command {
    private final RequestFactory requestFactory;
    private final CarDetailsService<AdditionEntity> additionEntityCarDetailsService;
    private final CarDetailsService<BodyEntity> bodyEntityCarDetailsService;
    private final CarDetailsService<EngineEntity> engineEntityCarDetailsService;
    private final CarDetailsService<SalonEntity> salonEntityCarDetailsService;
    private final CarDetailsService<SeparaterlyEntity> separaterlyEntityCarDetailsService;
    private final CarDetailsService<SuspensionEntity> suspensionEntityCarDetailsService;


    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String detailType = request.getParameter("type");
        String detailId = request.getParameter("id");
        if (proceed(request, detailType, detailId)) {
            return requestFactory.createForwardResponse(PagePath.DETAIL.getPath());
        }
        return requestFactory.createForwardResponse(PagePath.DETAILS_PAGE.getPath());
    }

    private boolean proceed(CommandRequest commandRequest, String detailType, String detailId) {
        switch (detailType) {
            case "engine": {
                return proceed(engineEntityCarDetailsService, detailId, commandRequest, "engine");
            }
            case "susp": {
                return proceed(suspensionEntityCarDetailsService, detailId, commandRequest, "susp");
            }
            case "body": {
                return proceed(bodyEntityCarDetailsService, detailId, commandRequest, "body");
            }
            case "salon": {
                return proceed(salonEntityCarDetailsService, detailId, commandRequest, "salon");
            }
            case "sep": {
                return proceed(separaterlyEntityCarDetailsService, detailId, commandRequest, "sep");
            }
            case "addition": {
                return proceed(additionEntityCarDetailsService, detailId, commandRequest, "addition");
            }
        }
        return false;
    }

    private boolean proceed(CarDetailsService<?> carDetailsService, String detailId, CommandRequest commandRequest, String type) {
        Optional<?> entity = carDetailsService.findById(Long.parseLong(detailId));
        if (entity.isPresent()) {
            switch (type) {
                case "engine": {
                    EngineEntity engine = (EngineEntity) entity.get();
                    commandRequest.addAttributeToJsp("id", detailId);
                    commandRequest.addAttributeToJsp("detail", engine);
                    commandRequest.addAttributeToJsp("type", type);
                    return true;

                }
                case "susp": {
                    SuspensionEntity suspension = (SuspensionEntity) entity.get();
                    commandRequest.addAttributeToJsp("id", detailId);
                    commandRequest.addAttributeToJsp("detail", suspension);
                    commandRequest.addAttributeToJsp("type", type);
                    return true;
                }
                case "body": {
                    BodyEntity body = (BodyEntity) entity.get();
                    commandRequest.addAttributeToJsp("id", detailId);
                    commandRequest.addAttributeToJsp("detail", body);
                    commandRequest.addAttributeToJsp("type", type);
                    return true;
                }
                case "salon": {
                    SalonEntity salon = (SalonEntity) entity.get();
                    commandRequest.addAttributeToJsp("id", detailId);
                    commandRequest.addAttributeToJsp("detail", salon);
                    commandRequest.addAttributeToJsp("type", type);
                    return true;
                }
                case "sep": {
                    SeparaterlyEntity separaterly = (SeparaterlyEntity) entity.get();
                    commandRequest.addAttributeToJsp("id", detailId);
                    commandRequest.addAttributeToJsp("detail", separaterly);
                    commandRequest.addAttributeToJsp("type", type);
                    return true;
                }
                case "addition": {
                    AdditionEntity addition = (AdditionEntity) entity.get();
                    commandRequest.addAttributeToJsp("id", detailId);
                    commandRequest.addAttributeToJsp("detail", addition);
                    commandRequest.addAttributeToJsp("type", type);
                    return true;
                }
            }
        }
        return false;
    }
}
