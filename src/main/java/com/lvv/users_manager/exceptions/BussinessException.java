package com.lvv.users_manager.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class BussinessException extends RuntimeException{

    private final int errorCode;
    private final HttpStatus status;
    private final String responseDescription;

    protected BussinessException(int errorCode, HttpStatus status, String responseDescription) {
        this.errorCode = errorCode;
        this.status = status;
        this.responseDescription = responseDescription;
    }

}