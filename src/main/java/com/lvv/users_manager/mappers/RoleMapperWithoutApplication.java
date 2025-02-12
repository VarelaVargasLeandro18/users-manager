package com.lvv.users_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.lvv.users_manager.entities.Role;
import com.lvv.users_manager.models.RoleDTO;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = { PermissionMapper.class }
)
public interface RoleMapperWithoutApplication {

    @Mapping(ignore = true, target = "application")
    RoleDTO toDTO(Role entity);

}
