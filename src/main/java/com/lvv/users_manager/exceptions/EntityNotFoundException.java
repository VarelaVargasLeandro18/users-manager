package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BussinessException {

    private static final int ERROR_CODE = 1000;
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    private static final String RESPONSE_DESCRIPTION = "Entity not found.";

    public EntityNotFoundException() {
        super(ERROR_CODE, STATUS, RESPONSE_DESCRIPTION);
    }

}