package org.zlasu.controler.filter;

import org.zlasu.controler.auth.LoginServlet;
import org.zlasu.model.employee.EmployeeAuth;
import org.zlasu.model.employee.EmployeeAuthDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest requestSer, ServletResponse responseSer,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) requestSer;
        HttpServletResponse response = (HttpServletResponse) responseSer;

        String path = request.getRequestURI();

        if (path.startsWith("/backend/login") || path.startsWith("/backend/register") || isValidToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        //filterChain.doFilter(request, response);
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
    }

    private static boolean isValidToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String tokenSession = (String) session.getAttribute("token");
        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader == null || !tokenHeader.equals(tokenSession)) {
            return false;
        }

        if (tokenSession != null && tokenHeader.equals(tokenSession)) {
            return true;
        }

        if (tokenSession == null && tokenHeader != null) {
            EmployeeAuthDao employeeAuthDao = new EmployeeAuthDao();
            EmployeeAuth employeeAuth = employeeAuthDao.readByToken(tokenHeader);

            if (employeeAuth != null) {
                LoginServlet.setUserSession(session, employeeAuth);
                return true;
            }
        }

        return false;
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
