package org.zlasu.controler.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {

    private String charsetEncoding = "utf-8";
    private String contentType = "application/json";

    public void doFilter(ServletRequest requestSer, ServletResponse responseSer,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) requestSer;
        HttpServletResponse response = (HttpServletResponse) responseSer;
        request.setCharacterEncoding(charsetEncoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(charsetEncoding);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Request-Headers", "X-Requested-With, accept, content-type, Authorization");

        filterChain.doFilter(requestSer, responseSer);
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
