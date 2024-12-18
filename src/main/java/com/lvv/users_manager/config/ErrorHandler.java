package com.lvv.users_manager.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.lvv.users_manager.exceptions.BussinessException;
import com.lvv.users_manager.models.BussinessErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

        private static final int UNEXPECTED_ERROR_CODE = 1002;
    private static final int ACCESS_DENIED_ERROR_CODE = 1003;
    private static final int UNAUTHORIZED_ERROR_CODE = 1004;
    private static final int RESOURCE_NOT_FOUND_ERROR_CODE = 1005;
    
    private static final String UNEXPECTED_ERROR_DESCRIPTION = "An unexpected error has ocurred in the application.";
    private static final String ACCESS_DENIED_DESCRIPTION = "You don't have permission to access this resource.";
    private static final String UNAUTHORIZED_DESCRIPTION = "You are not authorized to access this resource.";
    private static final String RESOURCE_NOT_FOUND_DESCRIPTION = "Resource not found.";

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<BussinessErrorResponseDTO> handleBussinessException(BussinessException ex, HttpServletRequest request) {
        BussinessErrorResponseDTO response = new BussinessErrorResponseDTO(ex.getResponseDescription(),
                ex.getErrorCode(), request.getRequestURI());
        return ResponseEntity.status(ex.getStatus())
                .body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BussinessErrorResponseDTO> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        BussinessErrorResponseDTO response = new BussinessErrorResponseDTO(ACCESS_DENIED_DESCRIPTION,
                ACCESS_DENIED_ERROR_CODE, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BussinessErrorResponseDTO> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        BussinessErrorResponseDTO response = new BussinessErrorResponseDTO(UNAUTHORIZED_DESCRIPTION,
                UNAUTHORIZED_ERROR_CODE, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<BussinessErrorResponseDTO> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        BussinessErrorResponseDTO response = new BussinessErrorResponseDTO(RESOURCE_NOT_FOUND_DESCRIPTION,
                RESOURCE_NOT_FOUND_ERROR_CODE, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BussinessErrorResponseDTO> handleException(Exception ex, HttpServletRequest request) {
        log.error("An unexpected error has ocurred trying to access to the resource '{}': {}", request.getRequestURI(), ex.getMessage(), ex);

        BussinessErrorResponseDTO response = new BussinessErrorResponseDTO(UNEXPECTED_ERROR_DESCRIPTION,
                UNEXPECTED_ERROR_CODE, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}