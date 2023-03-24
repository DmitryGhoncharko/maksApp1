package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import by.webproj.carshowroom.model.service.SimpleCarDetailsService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AddDetailCommand implements Command {
    private final RequestFactory requestFactory;
    private final CarDetailsService<EngineEntity> engineEntityCarDetailsService;
    private final CarDetailsService<SuspensionEntity> suspensionEntityCarDetailsService;
    private final CarDetailsService<BodyEntity> bodyEntityCarDetailsService;

    private final CarDetailsService<SalonEntity> salonEntityCarDetailsService;

    private final CarDetailsService<SeparaterlyEntity> separaterlyEntityCarDetailsService;

    private final CarDetailsService<AdditionEntity> additionEntityCarDetailsService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String detailType = request.getParameter("detailType");
        String detailName = request.getParameter("detailName");
        String detailWeight = request.getParameter("detailWeight");
        if (proceed(detailType, detailName, detailWeight)) {
            return requestFactory.createRedirectResponse("/controller?command=/");
        }
        request.addAttributeToJsp("error", "error");
        return requestFactory.createForwardResponse(PagePath.MAIN_PAGE.getPath());
    }

    private boolean proceed(String detailType, String detailName, String detailWeight) {
        if (detailType == null || detailName == null || detailWeight == null) {
            return false;
        }
        double parsedWeight = parceWeigthToDouble(detailWeight);
        switch (detailType) {
            case "1": {
                engineEntityCarDetailsService.add(detailName, parsedWeight);
                return true;
            }
            case "2": {
                suspensionEntityCarDetailsService.add(detailName, parsedWeight);
                return true;
            }
            case "3": {
                bodyEntityCarDetailsService.add(detailName, parsedWeight);
                return true;
            }
            case "4": {
                salonEntityCarDetailsService.add(detailName, parsedWeight);
                return true;
            }
            case "5": {
                separaterlyEntityCarDetailsService.add(detailName, parsedWeight);
                return true;
            }
            case "6": {
                additionEntityCarDetailsService.add(detailName, parsedWeight);
                return true;
            }
            default:
                return false;
        }
    }

    private double parceWeigthToDouble(String weight) {
        if (weight.trim().split(",").length > 1) {
            String[] splitted = weight.trim().split(",");
            return Double.parseDouble(splitted[0]) + (Double.parseDouble(splitted[1]) / 10 * splitted[1].length());
        } else if(weight.trim().split("\\.").length > 1){
            String[] splitted = weight.trim().split("\\.");
            return Double.parseDouble(splitted[0]) + (Double.parseDouble(splitted[1]) / 10 * splitted[1].length());
        }else {
            return Double.parseDouble(weight.trim());
        }
    }
}
