package com.lvv.users_manager.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(
                Integer id,
                String email,
                String username,
                String password,
                String firstname,
                String lastname,
                LocalDate birthdate,
                ApplicationDTO application) {
}