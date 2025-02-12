package com.lvv.users_manager.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.lvv.users_manager.entities.User;
import com.lvv.users_manager.helpers.PasswordHasherHelper;
import com.lvv.users_manager.models.UserDTO;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = { ApplicationMapper.class, RoleMapperWithoutApplication.class }
)
public interface UserMapper {

    @Mapping( ignore = true, target = "password" )
    UserDTO toDTO(User entity);
    
    @Mapping( source = "password", target = "password", qualifiedByName = "hashPassword" )
    @Mapping( ignore = true, target = "roles" )
    User toEntity(UserDTO dto, @Context PasswordHasherHelper passwordHasherHelper);
    
    @Named("hashPassword")
    default String hashPassword(String password, @Context PasswordHasherHelper passwordHasherHelper) {
        return passwordHasherHelper.hashPassword(password);
    }

}