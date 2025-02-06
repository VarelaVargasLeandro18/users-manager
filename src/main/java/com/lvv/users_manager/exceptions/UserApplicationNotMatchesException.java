package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;

public class UserApplicationNotMatchesException extends BussinessException {

    private static final int ERROR_CODE = 1013;
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String RESPONSE_DESCRIPTION = "The user's application uuid does not match with this application uuid.";

    public UserApplicationNotMatchesException() {
        super(ERROR_CODE, STATUS, RESPONSE_DESCRIPTION);
    }
}