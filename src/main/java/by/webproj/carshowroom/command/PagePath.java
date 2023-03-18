package by.webproj.carshowroom.command;

public enum PagePath {
    MAIN_PAGE("/WEB-INF/jsp/main.jsp"), LOGIN_PAGE("/WEB-INF/jsp/login.jsp"), INDEX_PATH("/"), REGISTRATION_PAGE("/WEB-INF/jsp/registration.jsp"),
    TASK_PAGE("/WEB-INF/jsp/task.jsp"), DETAILS_PAGE("/WEB-INF/jsp/details.jsp"), DETAIL("/WEB-INF/jsp/detail.jsp");
    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
