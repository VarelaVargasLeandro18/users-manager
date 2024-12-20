package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;

public class JwtNoPresentException extends BussinessException {

    private static final int ERROR_CODE = 1010;
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    private static final String RESPONSE_DESCRIPTION = "Jwt no present in the request.";

    public JwtNoPresentException() {
        super(ERROR_CODE, STATUS, RESPONSE_DESCRIPTION);
    }

}