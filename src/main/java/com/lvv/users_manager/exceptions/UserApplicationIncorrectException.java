package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;

public class UserApplicationIncorrectException extends BussinessException {

    private static final int ERROR_CODE = 1009;
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    private static final String RESPONSE_DESCRIPTION = "User's application is incorrect.";

    public UserApplicationIncorrectException() {
        super(ERROR_CODE, STATUS, RESPONSE_DESCRIPTION);
    }

}