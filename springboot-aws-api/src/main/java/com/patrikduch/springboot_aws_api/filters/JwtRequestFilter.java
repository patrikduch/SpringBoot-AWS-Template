package com.patrikduch.springboot_aws_api.filters;

import com.patrikduch.springboot_aws_api.service.ExtendedUserService;
import com.patrikduch.springboot_aws_api.utils.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT filter for authorization incoming HTTP requests.
 * @author Patrik Duch
 */
@Service
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil _jwtUtil;

    @Qualifier("UserDetailsService")
    @Autowired
    private ExtendedUserService _userService;

    /**
     * Inherited and overridden method filter method with customization for handling JWT inside HTTP requests.
     * @param request Instance of incoming HTTP request.
     * @param response Instance of incoming HTTP response.
     * @param filterChain Instance of current sequence of pipeline operations,
     *                    which enables to add new action into final pipeline.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authorizationHeader != null) {

            try {

                if (authorizationHeader.startsWith("Bearer ")) {

                    jwt = authorizationHeader.substring(7);
                    username = _jwtUtil.extractUsername(jwt);
                }

            } catch (MalformedJwtException ex) { // If token is invalid

                response.resetBuffer();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("Content-Type", "application/json");
                response.getOutputStream().print("{\"errorMessage\":\"JWT token is invalid!\"}");
                response.flushBuffer(); //marks response as committed -- if we don't do this the request will go through normally!

                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)  {

            UserDetails userDetails =  this._userService.loadUserByUsername(username);

            if (_jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}