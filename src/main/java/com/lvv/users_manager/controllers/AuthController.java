package com.lvv.users_manager.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvv.users_manager.models.JWTResponseDTO;
import com.lvv.users_manager.models.UserLogInDTO;
import com.lvv.users_manager.services.LogInService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final LogInService logInService;

    @PostMapping("/log-in")
    public JWTResponseDTO logIn(@RequestBody UserLogInDTO userLoginData) {
        return new JWTResponseDTO(logInService.logIn(userLoginData));
    }

}