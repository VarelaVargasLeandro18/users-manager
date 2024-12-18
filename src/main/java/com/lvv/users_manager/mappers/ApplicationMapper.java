package com.lvv.users_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.lvv.users_manager.entities.Application;
import com.lvv.users_manager.models.ApplicationDTO;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ApplicationMapper {

    ApplicationDTO toDTO(Application entity);

    Application toEntity(ApplicationDTO dto);

}