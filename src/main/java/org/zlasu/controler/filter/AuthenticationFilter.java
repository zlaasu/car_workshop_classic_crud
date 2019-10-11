package org.zlasu.controler.filter;

import org.zlasu.controler.Login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest requestSer, ServletResponse responseSer,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) requestSer;
        HttpServletResponse response = (HttpServletResponse) responseSer;

        String path = ((HttpServletRequest) request).getRequestURI();

        String token = request.getHeader("Token");

        if (path.startsWith("/login/") || path.startsWith("/register/") || Login.checkTokenForUser(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
