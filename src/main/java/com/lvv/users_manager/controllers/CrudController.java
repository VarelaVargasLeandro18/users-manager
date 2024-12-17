package com.lvv.users_manager.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface CrudController<ID, DTO> {

    Page<DTO> getAll(int pageNumber, int pageSize);

    DTO getOne(ID id);

    DTO create(DTO dto);

    DTO update(ID id, DTO dto);

    ResponseEntity<Void> delete(ID id);

}