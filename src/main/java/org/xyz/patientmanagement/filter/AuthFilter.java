package org.xyz.patientmanagement.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.xyz.patientmanagement.util.Util.isLoggedIn;
import static org.xyz.patientmanagement.util.Util.isPathAllowedToAll;

/**
 * @author inanmashrur
 * @since 1/12/23
 */
@Component
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    private static final String LOGIN_PAGE = "/login";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        setResponseHeaders(httpServletResponse);

        if (isPathAllowedToAll(httpServletRequest.getServletPath()) || isLoggedIn(session)) {
            chain.doFilter(request, response);

            return;
        }

        httpServletResponse.sendRedirect(LOGIN_PAGE);
    }

    private void setResponseHeaders(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        httpServletResponse.setHeader("Expires", "0");
        httpServletResponse.setHeader("Pragma", "no-cache");
    }
}
