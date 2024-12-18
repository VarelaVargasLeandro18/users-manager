package com.lvv.users_manager.models;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class BussinessErrorResponseDTO {

    private final String message;
    private final int errorCode;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final String path;

    public BussinessErrorResponseDTO(String message, int errorCode, String path) {
        this.message = message;
        this.errorCode = errorCode;
        this.path = path;
    }

}