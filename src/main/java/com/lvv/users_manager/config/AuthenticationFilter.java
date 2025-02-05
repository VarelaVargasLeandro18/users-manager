package com.lvv.users_manager.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.lvv.users_manager.exceptions.JwtNoPresentException;
import com.lvv.users_manager.exceptions.UserApplicationNotMatchesException;
import com.lvv.users_manager.utils.JWTUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final String LOGIN_URI = "/auth/log-in";

    private final HandlerExceptionResolver exceptionResolver;

    @Value("${user-manager.JWT-SECRET}")
    private String jwtSecret;

    @Value("${user-manager.APP-UID}")
    private String appUID;

    public AuthenticationFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {        
        String jwt = getJwtFromRequest(request);

        try {
            Claims jwtClaims = JWTUtils.getClaims(jwt, this.jwtSecret);
            String username = jwtClaims.getSubject();
            String jwtAppUID = (String) jwtClaims.get(JWTUtils.APPLICATION_CLAIM);

            if (jwtAppUID == null || !jwtAppUID.equals(this.appUID)) {
                throw new UserApplicationNotMatchesException();
            }

            List<String> rolesFromJWT = (List<String>) jwtClaims.get(JWTUtils.ROLES_CLAIM);
            List<String> permissionsFromJWT = (List<String>) jwtClaims.get(JWTUtils.PERMISSIONS_CLAIM);

            List<SimpleGrantedAuthority> authorities = rolesFromJWT.stream()
                .map(role -> ROLE_PREFIX + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
            authorities.addAll(permissionsFromJWT.stream()
                .map(SimpleGrantedAuthority::new)
                .toList());

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);   

            SecurityContextHolder.getContext().setAuthentication(authentication);   
            filterChain.doFilter(request, response);      
        } catch( Exception ex ) {
            exceptionResolver.resolveException(request, response, null, ex);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        throw new JwtNoPresentException();
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return request.getServletPath().equals(LOGIN_URI);
    }


}