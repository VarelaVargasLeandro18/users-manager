package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

public class UserNotFoundException extends BussinessException {

    private static final int ERROR_CODE = 1007;
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    private static final String RESPONSE_DESCRIPTION = "User not found with data: ";

    public UserNotFoundException(String email, String username) {
        super(ERROR_CODE, STATUS, RESPONSE_DESCRIPTION + "email: " + 
            ObjectUtils.nullSafeToString(email) 
            + ", username: " + 
            ObjectUtils.nullSafeToString(username));
    }

}