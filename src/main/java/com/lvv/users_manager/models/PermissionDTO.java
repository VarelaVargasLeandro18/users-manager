package com.lvv.users_manager.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PermissionDTO(
                Integer id,
                String name) {
}