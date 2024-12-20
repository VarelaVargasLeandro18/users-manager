package com.lvv.users_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.lvv.users_manager.entities.Role;
import com.lvv.users_manager.models.RoleDTO;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = { ApplicationMapper.class }
)
public interface RoleMapper {

    RoleDTO toDTO(Role entity);

    Role toEntity(RoleDTO dto);

}