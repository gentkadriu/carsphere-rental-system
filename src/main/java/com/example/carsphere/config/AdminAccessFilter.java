package com.example.carsphere.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AdminAccessFilter extends GenericFilterBean {

    private static final AntPathRequestMatcher ADMIN_DASHBOARD_MATCHER = new AntPathRequestMatcher("/admin/dashboard");
    private static final AntPathRequestMatcher ADMIN_EDIT_MATCHER = new AntPathRequestMatcher("/admin/edit/**"); // Restrict /admin/edit/{id}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        if (ADMIN_DASHBOARD_MATCHER.matches(httpRequest) || ADMIN_EDIT_MATCHER.matches(httpRequest)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // If user is authenticated but not "admin", redirect them to home page
            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();
                if (!"admin".equals(username)) {
                    httpResponse.sendRedirect("/");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }
}
