package com.lvv.users_manager.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvv.users_manager.models.UserDTO;
import com.lvv.users_manager.services.UsersServices;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
@Tag(name = "User Operations", description = "APIs for operation on User")
public class UsersController extends CrudControllerAbstractImpl<UserDTO, Integer> {

    public UsersController(UsersServices usersServices) {
        super(usersServices);
    }
    
}