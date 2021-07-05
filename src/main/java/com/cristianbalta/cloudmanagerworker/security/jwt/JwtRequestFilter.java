package com.cristianbalta.cloudmanagerworker.security.jwt;

import com.cristianbalta.cloudmanagerworker.security.model.ErrorResponse;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, HttpServletRequest request, Throwable ex) {
        response.setStatus(status.value());
        response.setContentType("application/json");

        try {
            ErrorResponse errorResponse = new ErrorResponse(Integer.toString(response.getStatus()), ex.getMessage(), request.getRequestURI());
            String errorString = new Gson().toJson(errorResponse);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(errorString);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String jwt;
        String username = null;
        if (!authorizationHeader.equals("") && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                JwtUtil.validateToken(jwt);
            } catch (Exception exception) {
                setErrorResponse(HttpStatus.UNAUTHORIZED, response, request, exception);
                return;
            }
            try {
                username = JwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (authorizationHeader.equals(""))
                setErrorResponse(HttpStatus.UNAUTHORIZED, response, request, new Exception("MISSING_TOKEN"));
            else
                setErrorResponse(HttpStatus.UNAUTHORIZED, response, request, new Exception("INVALID_HEADER"));
            return;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("authority"));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }
}