package com.lvv.users_manager.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoleDTO(
    Integer id, 
    String name,
    ApplicationDTO application) {
}