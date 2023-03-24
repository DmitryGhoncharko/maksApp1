package by.webproj.carshowroom.command;

import by.webproj.carshowroom.command.delete.*;
import by.webproj.carshowroom.command.page.*;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.controller.SimpleRequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.connection.HikariCPConnectionPool;
import by.webproj.carshowroom.model.dao.*;
import by.webproj.carshowroom.model.service.*;
import by.webproj.carshowroom.securiy.BcryptWithSaltHasherImpl;
import by.webproj.carshowroom.securiy.PasswordHasher;
import by.webproj.carshowroom.validator.SimpleUserValidator;
import by.webproj.carshowroom.validator.UserValidator;


public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();
    private final UserDao simplePageDao = new SimpleUserDao(hikariCPConnectionPool);
    private final UserValidator simplePageServiceValidator = new SimpleUserValidator();
    private final PasswordHasher bcryptWithSaltHasher = new BcryptWithSaltHasherImpl();
    private final EngineCarDetailsDao engineCarDetailsDaoCarDetailsDao = new EngineCarDetailsDao(hikariCPConnectionPool);
    private final AdditionCarDetailsDao additionCarDetailsDao = new AdditionCarDetailsDao(hikariCPConnectionPool);
    private final BodyCarDetailsDao bodyCarDetailsDao = new BodyCarDetailsDao(hikariCPConnectionPool);
    private final SalonCarDetailsDao salonCarDetailsDao = new SalonCarDetailsDao(hikariCPConnectionPool);
    private final SeparateCarDetailsDao separateCarDetailsDao = new SeparateCarDetailsDao(hikariCPConnectionPool);
    private final SuspensionCarDetailsDao suspensionCarDetailsDao = new SuspensionCarDetailsDao(hikariCPConnectionPool);
    private final CarDetailsService<AdditionEntity> additionEntityCarDetailsService = new SimpleCarDetailsService<>(additionCarDetailsDao);
    private final CarDetailsService<SeparaterlyEntity> separaterlyEntityCarDetailsService = new SimpleCarDetailsService<>(separateCarDetailsDao);
    private final CarDetailsService<SalonEntity> salonEntityCarDetailsService = new SimpleCarDetailsService<>(salonCarDetailsDao);
    private final CarDetailsService<BodyEntity> bodyEntityCarDetailsService = new SimpleCarDetailsService<>(bodyCarDetailsDao);
    private final CarDetailsService<SuspensionEntity> suspensionEntityCarDetailsService = new SimpleCarDetailsService<>(suspensionCarDetailsDao);
    private final CarDetailsService<EngineEntity> engineEntityCarDetailsService = new SimpleCarDetailsService<>(engineCarDetailsDaoCarDetailsDao);
    private final UserService simpleUserService = new SimpleUserService(simplePageServiceValidator, simplePageDao, bcryptWithSaltHasher);

    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();

    public Command lookup(String commandName) {

        switch (commandName) {
            case "logincmnd":
                return new LoginCommand(simpleUserService, simpleRequestFactory);
            case "login":
                return new ShowLoginPageCommand(simpleRequestFactory);
            case "logout":
                return new LogoutCommand(simpleRequestFactory);
            case "registration":
                return new ShowRegistrationPageCommand(simpleRequestFactory);
            case "registrationcmnd":
                return new RegistrationCommand(simpleUserService, simpleRequestFactory);
            case "addNewDetail":
                return new AddDetailCommand(simpleRequestFactory, engineEntityCarDetailsService, suspensionEntityCarDetailsService, bodyEntityCarDetailsService, salonEntityCarDetailsService, separaterlyEntityCarDetailsService, additionEntityCarDetailsService);
            case "task":
                return new ShowTaskPageCommand(simpleRequestFactory,additionEntityCarDetailsService,bodyEntityCarDetailsService,engineEntityCarDetailsService,salonEntityCarDetailsService,separaterlyEntityCarDetailsService,suspensionEntityCarDetailsService);
            case "allDetails":
                return new ShowAllDetailsPageCommand(simpleRequestFactory, additionEntityCarDetailsService, bodyEntityCarDetailsService, engineEntityCarDetailsService, salonEntityCarDetailsService, separaterlyEntityCarDetailsService, suspensionEntityCarDetailsService);
            case "deleteAddition":
                return new DeleteAdditionCommand(simpleRequestFactory, additionEntityCarDetailsService);
            case "deleteSep":
                return new DeleteSepCommand(simpleRequestFactory, separaterlyEntityCarDetailsService);
            case "deleteSalon":
                return new DeleteSalonCommand(simpleRequestFactory, salonEntityCarDetailsService);
            case "deleteBody":
                return new DeleteBodyCommand(simpleRequestFactory, bodyEntityCarDetailsService);
            case "deleteSuspension":
                return new DeleteSuspensionCommand(simpleRequestFactory, suspensionEntityCarDetailsService);
            case "deleteEngine":
                return new DeleteEngineCommand(simpleRequestFactory, engineEntityCarDetailsService);
            case "getDetailById": {
                return new GetDetailByTypeIdCommand(simpleRequestFactory, additionEntityCarDetailsService, bodyEntityCarDetailsService, engineEntityCarDetailsService, salonEntityCarDetailsService, separaterlyEntityCarDetailsService, suspensionEntityCarDetailsService);
            }
            case "updateDetail": {
                return new UpdateDetailByIdAndTypeCommand(simpleRequestFactory, additionEntityCarDetailsService, bodyEntityCarDetailsService, engineEntityCarDetailsService, salonEntityCarDetailsService, separaterlyEntityCarDetailsService, suspensionEntityCarDetailsService);
            }
            case "addToSession":{
                return new AddToSessionCommand(simpleRequestFactory,additionEntityCarDetailsService,bodyEntityCarDetailsService,engineEntityCarDetailsService,salonEntityCarDetailsService,separaterlyEntityCarDetailsService,suspensionEntityCarDetailsService);
            }
            case "clearSes":{
                return new ClearSessionCommand(simpleRequestFactory);
            }
            case "delses":{
                return new DeleteFromSessionCommand(simpleRequestFactory,additionEntityCarDetailsService,bodyEntityCarDetailsService,engineEntityCarDetailsService,salonEntityCarDetailsService,separaterlyEntityCarDetailsService,suspensionEntityCarDetailsService);
            }
            case "deleteByIdAndType":{
                return new DeleteDetailByIdAndType(simpleRequestFactory,additionEntityCarDetailsService,bodyEntityCarDetailsService,engineEntityCarDetailsService,salonEntityCarDetailsService,separaterlyEntityCarDetailsService,suspensionEntityCarDetailsService);
            }
            default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }
    }
}
