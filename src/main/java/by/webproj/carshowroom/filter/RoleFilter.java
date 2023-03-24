package by.webproj.carshowroom.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String commandName = request.getParameter("command");
        if (httpServletRequest.getSession(false) == null || httpServletRequest.getSession(false).getAttribute("user") == null) {
            if (commandName == null || commandName.equals("login") || commandName.equals("logout") || commandName.equals("logincmnd") || commandName.equals("registration") || commandName.equals("registrationcmnd") || commandName.equals("/")) {
                chain.doFilter(request,response);
            }else {
                httpServletResponse.sendRedirect("/controller?command=login");
            }
        }else {
            chain.doFilter(request, response);
        }
    }
}