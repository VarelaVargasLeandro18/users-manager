package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;

public class JwtDecodingException extends BussinessException {

    private static final int ERROR_CODE = 1011;
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    private static final String RESPONSE_DESCRIPTION = "Error decoding JWT.";

    public JwtDecodingException() {
        super(ERROR_CODE, STATUS, RESPONSE_DESCRIPTION);
    }

}