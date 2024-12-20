package com.lvv.users_manager.models;

public record UserLogInDTO(
    String email,
    String username,
    String password,
    String applicationUUID
) {}