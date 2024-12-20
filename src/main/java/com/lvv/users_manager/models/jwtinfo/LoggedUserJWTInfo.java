package com.lvv.users_manager.models.jwtinfo;

import java.util.List;

public record LoggedUserJWTInfo(
    String username,
    String email,
    String applicationUUID,
    List<String> roles,
    List<String> permissions
    ) {
}