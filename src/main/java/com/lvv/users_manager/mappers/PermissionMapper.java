package com.lvv.users_manager.mappers;

import java.security.Permission;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.lvv.users_manager.models.PermissionDTO;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PermissionMapper {

    PermissionDTO toDTO(Permission entity);

}