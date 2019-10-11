package org.zlasu.controler.filter;

import org.zlasu.controler.auth.Login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest requestSer, ServletResponse responseSer,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) requestSer;
        HttpServletResponse response = (HttpServletResponse) responseSer;

        String path = request.getRequestURI();
        String token = request.getHeader("Token");

        if (path.startsWith("/backend/login") || path.startsWith("/backend/register") || Login.checkTokenForUser(token)) {
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
