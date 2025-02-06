package com.lvv.users_manager.services;

import com.lvv.users_manager.models.UserLogInDTO;

public interface LogInService {

    static final String JWT_HEADER_NAME = "Authorization";
    static final String JWT_PREFIX = "Bearer ";
    static final String JWT_TOKEN_TYPE = "JWT";
    static final String PASSWORD_ERROR = "Invalid password.";

    String logIn(UserLogInDTO userLoginData);

}