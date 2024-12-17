package com.lvv.users_manager.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lvv.users_manager.exceptions.EntityAlreadyExistsException;
import com.lvv.users_manager.exceptions.EntityNotFoundException;
import com.lvv.users_manager.services.CrudService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CrudServiceAbstract<R extends JpaRepository<E, ID>, E, ID, DTO> implements CrudService<ID, DTO> {

    protected final R repository;

    protected CrudServiceAbstract(R repository) {
        this.repository = repository;
    }

    @Override
    public Page<DTO> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return this.repository.findAll(pageRequest).map(this::toDTO);
    }

    @Override
    public DTO getOne(ID id) {
        Optional<E> entity = this.repository.findById(id);
        return entity.map(this::toDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public DTO create(DTO dto) {
        E toSave = this.toEntity(dto);

        this.isEntityExists(toSave);

        E entity = this.repository.save(toSave);
        return this.toDTO(entity);
    }

    @Override
    public DTO update(ID id, DTO dto) {
        E toUpdate = this.toEntity(dto);

        if (!this.repository.existsById(id))
            throw new EntityNotFoundException();

        E entity = this.repository.save(toUpdate);
        return this.toDTO(entity);
    }

    @Override
    public void delete(ID id) {
        if (!this.repository.existsById(id))
            throw new EntityNotFoundException();

        this.repository.deleteById(id);
    }

    protected abstract DTO toDTO(E entity);

    protected abstract E toEntity(DTO dto);

    protected abstract ID getEntityId(E entity);

    protected abstract boolean isEntityExists(E entity) throws EntityAlreadyExistsException;

}