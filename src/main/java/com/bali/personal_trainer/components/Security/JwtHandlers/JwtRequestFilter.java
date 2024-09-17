package com.bali.personal_trainer.components.Security.JwtHandlers;


import com.bali.personal_trainer.services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {


        final String authorizationHeader = request.getHeader("Authorization");

        String userId = null;
        String jwt = null;

        try
        {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
            {
                jwt = authorizationHeader.substring(7);
                userId = jwtUtility.extractUserId(jwt);


            }

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                UserDetails userDetails = (UserDetails) userService.get(Integer.parseInt(userId));
                if (jwtUtility.validateToken(jwt, userId))
                {
                    // Extract role from the JWT
                    String role = jwtUtility.extractRole(jwt);
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, List.of(authority));
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        catch (Exception e)
        {
            // In case of a token signature error, trigger the authentication entry point
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid token signature\", \"message\": \"" + e.getMessage() + "\"}");
            return; // Stop the filter chain since an error occurred
        }


        chain.doFilter(request, response);
    }
}