package by.webproj.carshowroom.command.page;

import by.webproj.carshowroom.command.*;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import by.webproj.carshowroom.model.service.SimpleCarDetailsService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowAllDetailsPageCommand implements Command {
    private final RequestFactory requestFactory;
    private final CarDetailsService<AdditionEntity> additionEntityCarDetailsService;
    private final CarDetailsService<BodyEntity> bodyEntityCarDetailsService;
    private final CarDetailsService<EngineEntity> engineEntityCarDetailsService;
    private final CarDetailsService<SalonEntity> salonEntityCarDetailsService;
    private final CarDetailsService<SeparaterlyEntity> separaterlyEntityCarDetailsService;
    private final CarDetailsService<SuspensionEntity> suspensionEntityCarDetailsService;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        request.addAttributeToJsp("additions",getAllAdditions());
        request.addAttributeToJsp("bodies",getAllBodies());
        request.addAttributeToJsp("engines",getAllEngines());
        request.addAttributeToJsp("salons",getAllSalons());
        request.addAttributeToJsp("separaters",getAllSeparaters());
        request.addAttributeToJsp("suspensions",getAllSuspensions());
        return requestFactory.createForwardResponse(PagePath.DETAILS_PAGE.getPath());
    }

    private List<AdditionEntity> getAllAdditions() {
        return additionEntityCarDetailsService.findAll();
    }

    private List<BodyEntity> getAllBodies() {
        return bodyEntityCarDetailsService.findAll();
    }

    private List<EngineEntity> getAllEngines() {
        return engineEntityCarDetailsService.findAll();
    }

    private List<SalonEntity> getAllSalons() {
        return salonEntityCarDetailsService.findAll();
    }

    private List<SeparaterlyEntity> getAllSeparaters() {
        return separaterlyEntityCarDetailsService.findAll();
    }

    private List<SuspensionEntity> getAllSuspensions() {
        return suspensionEntityCarDetailsService.findAll();
    }
}
