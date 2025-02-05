package com.lvv.users_manager.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.lvv.users_manager.entities.Permission;
import com.lvv.users_manager.entities.Role;
import com.lvv.users_manager.entities.User;
import com.lvv.users_manager.exceptions.UnknownUserApplicationException;
import com.lvv.users_manager.exceptions.UserApplicationIncorrectException;
import com.lvv.users_manager.exceptions.UserNotFoundException;
import com.lvv.users_manager.helpers.PasswordHasherHelper;
import com.lvv.users_manager.models.UserLogInDTO;
import com.lvv.users_manager.models.jwtinfo.LoggedUserJWTInfo;
import com.lvv.users_manager.repositories.UsersRepository;
import com.lvv.users_manager.services.LogInService;
import com.lvv.users_manager.utils.JWTUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogInServiceImpl implements LogInService {

    private final UsersRepository usersRepository;
    private final PasswordHasherHelper passwordHasherHelper;

    @Value("${user-manager.JWT-SECRET}")
    private String jwtSecret;

    @Override
    public String logIn(UserLogInDTO userLoginData) {
        String email = ObjectUtils.nullSafeToString(userLoginData.email());
        String username = ObjectUtils.nullSafeToString(userLoginData.username());

        User user = this.usersRepository.findByEmail(email)
            .orElse( this.usersRepository.findByUsername(username).orElse(null) );

        if (user == null) {
            throw new UserNotFoundException(email, username);
        }

        if ( userLoginData.applicationUUID() == null )
            throw new UnknownUserApplicationException();

        if ( !userLoginData.applicationUUID().equals(user.getApplication().getUuid()) )
            throw new UserApplicationIncorrectException();

        if ( this.passwordHasherHelper.checkPassword(userLoginData.password(), user.getPassword()) ) {
            return JWTUtils.generateToken(this.createLoggedUserJWTInfo(user), this.jwtSecret);
        }
        
        return LogInService.PASSWORD_ERROR;
    }

    private LoggedUserJWTInfo createLoggedUserJWTInfo(User user) {
        return new LoggedUserJWTInfo(
                user.getUsername(),
                user.getEmail(),
                user.getApplication().getUuid(),
                user.getRoles().stream().map(Role::getName).toList(),
                user.getRoles().stream().map(Role::getPermissions)
                        .flatMap(permissions -> permissions.stream().map(Permission::getName)).toList());
    }

}
