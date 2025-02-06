package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;

public class UnknownUserApplicationException extends BussinessException {

    private static final int ERROR_CODE = 1008;
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    private static final String RESPONSE_DESCRIPTION = "User's application not defined.";

    public UnknownUserApplicationException() {
        super(ERROR_CODE, STATUS, RESPONSE_DESCRIPTION);
    }

}