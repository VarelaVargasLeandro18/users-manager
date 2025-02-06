package com.lvv.users_manager.services.impl;

import org.springframework.stereotype.Service;

import com.lvv.users_manager.entities.Application;
import com.lvv.users_manager.entities.User;
import com.lvv.users_manager.exceptions.EntityAlreadyExistsException;
import com.lvv.users_manager.mappers.UserMapper;
import com.lvv.users_manager.helpers.PasswordHasherHelper;
import com.lvv.users_manager.models.UserDTO;
import com.lvv.users_manager.repositories.ApplicationRepository;
import com.lvv.users_manager.repositories.UsersRepository;
import com.lvv.users_manager.services.UsersServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersServicesImpl extends CrudServiceAbstract<UsersRepository, User, Integer, UserDTO> implements UsersServices {

    private final UserMapper userMapper;
    private final ApplicationRepository applicationRepository;
    private final PasswordHasherHelper passwordHasherHelper;

    public UsersServicesImpl(UsersRepository usersRepository, UserMapper userMapper, ApplicationRepository applicationRepository, PasswordHasherHelper passwordHasherHelper) {
        super(usersRepository);
        this.userMapper = userMapper;
        this.applicationRepository = applicationRepository;
        this.passwordHasherHelper = passwordHasherHelper;
    }

    @Override
    protected UserDTO toDTO(User entity) {
        Application application = this.applicationRepository.findById(entity.getApplication().getUuid()).orElse(null);
        entity.setApplication(application);
        return this.userMapper.toDTO(entity);
    }

    @Override
    protected User toEntity(UserDTO dto) {
        return this.userMapper.toEntity(dto, this.passwordHasherHelper);
    }

    @Override
    protected Integer getEntityId(User entity) {
        return entity.getId();
    }

    @Override
    protected boolean isEntityExists(User entity) {
        boolean existsByEmail = this.repository.existsByEmail(entity.getEmail());
        boolean existsByUsername = this.repository.existsByUsername(entity.getUsername());

        if ( existsByEmail ) {
            throw new EntityAlreadyExistsException(" the email " + entity.getEmail());
        } else if ( existsByUsername ) {
            throw new EntityAlreadyExistsException(" the username " + entity.getUsername());
        }

        return true;
    }

    @Override
    protected boolean isEntityExistsById(User entity) {
        return this.repository.existsById(entity.getId());
    }
    
}