package by.webproj.carshowroom.command.page;

import by.webproj.carshowroom.command.Command;
import by.webproj.carshowroom.command.CommandRequest;
import by.webproj.carshowroom.command.CommandResponse;
import by.webproj.carshowroom.command.PagePath;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
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
        String sort = request.getParameter("sort");
        List<AdditionEntity> additionEntities = getAllAdditions();
        List<BodyEntity> bodyEntities = getAllBodies();
        List<EngineEntity> engineEntities = getAllEngines();
        List<SalonEntity> salonEntities = getAllSalons();
        List<SeparaterlyEntity> separaterlyEntities = getAllSeparaters();
        List<SuspensionEntity> suspensionEntities = getAllSuspensions();
        if (sort != null) {
            additionEntities.sort(Comparator.comparingDouble(AdditionEntity::getWeight));
            bodyEntities.sort(Comparator.comparingDouble(BodyEntity::getWeight));
            engineEntities.sort(Comparator.comparingDouble(EngineEntity::getWeight));
            salonEntities.sort(Comparator.comparingDouble(SalonEntity::getWeight));
            separaterlyEntities.sort(Comparator.comparingDouble(SeparaterlyEntity::getWeight));
            suspensionEntities.sort(Comparator.comparingDouble(SuspensionEntity::getWeight));
        }
        request.addAttributeToJsp("additions", additionEntities);
        request.addAttributeToJsp("bodies", bodyEntities);
        request.addAttributeToJsp("engines", engineEntities);
        request.addAttributeToJsp("salons", salonEntities);
        request.addAttributeToJsp("separaters", separaterlyEntities);
        request.addAttributeToJsp("suspensions", suspensionEntities);
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
