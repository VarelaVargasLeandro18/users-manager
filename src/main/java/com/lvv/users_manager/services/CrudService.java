package com.lvv.users_manager.services;

import org.springframework.data.domain.Page;

public interface CrudService<ID, DTO> {

    Page<DTO> getAll(int pageNumber, int pageSize);

    DTO getOne(ID id);

    DTO create(DTO dto);

    DTO update(DTO dto);

    void delete(ID id);

}