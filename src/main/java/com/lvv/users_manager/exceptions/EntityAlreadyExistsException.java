package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BussinessException{

    private static final int ERROR_CODE = 1001;
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String RESPONSE_DESCRIPTION = "Entity already exists having ";

    public EntityAlreadyExistsException(String fields) {
        super(ERROR_CODE, STATUS, RESPONSE_DESCRIPTION + fields);
    }

}