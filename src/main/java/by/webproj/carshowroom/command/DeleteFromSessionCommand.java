package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DeleteFromSessionCommand implements Command{
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
            return requestFactory.createRedirectResponse("/controller?command=task");
        }
        return requestFactory.createRedirectResponse("/controller?command=task");
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
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("engines");
                    if(objectOptional.isPresent()){
                        List<EngineEntity> list = (List<EngineEntity>) objectOptional.get();
                        list.remove(engine);
                        commandRequest.removeFromSession("engines");
                        commandRequest.addToSession("engines",list);
                    }
                    return true;

                }
                case "susp": {
                    SuspensionEntity suspension = (SuspensionEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("suspensions");
                    if(objectOptional.isPresent()){
                        List<SuspensionEntity> list = (List<SuspensionEntity>) objectOptional.get();
                        list.remove(suspension);
                        commandRequest.removeFromSession("suspensions");
                        commandRequest.addToSession("suspensions",list);
                    }
                    return true;
                }
                case "body": {
                    BodyEntity body = (BodyEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("bodies");
                    if(objectOptional.isPresent()){
                        List<BodyEntity> list = (List<BodyEntity>) objectOptional.get();
                        list.remove(body);
                        commandRequest.removeFromSession("bodies");
                        commandRequest.addToSession("bodies",list);
                    }
                    return true;
                }
                case "salon": {
                    SalonEntity salon = (SalonEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("salons");
                    if(objectOptional.isPresent()){
                        List<SalonEntity> list = (List<SalonEntity>) objectOptional.get();
                        list.remove(salon);
                        commandRequest.removeFromSession("salons");
                        commandRequest.addToSession("salons",list);
                    }
                    return true;
                }
                case "sep": {
                    SeparaterlyEntity separaterly = (SeparaterlyEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("separaters");
                    if(objectOptional.isPresent()){
                        List<SeparaterlyEntity> list = (List<SeparaterlyEntity>) objectOptional.get();
                        list.remove(separaterly);
                        commandRequest.removeFromSession("separaters");
                        commandRequest.addToSession("separaters",list);
                    }
                    return true;
                }
                case "addition": {
                    AdditionEntity addition = (AdditionEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("additions");
                    if(objectOptional.isPresent()){
                        List<AdditionEntity> list = (List<AdditionEntity>) objectOptional.get();
                        list.remove(addition);
                        commandRequest.removeFromSession("additions");
                        commandRequest.addToSession("additions",list);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
