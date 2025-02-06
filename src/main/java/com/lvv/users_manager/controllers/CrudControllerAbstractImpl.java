package com.lvv.users_manager.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lvv.users_manager.models.BussinessErrorResponseDTO;
import com.lvv.users_manager.services.CrudService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@ApiResponses(
    value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = BussinessErrorResponseDTO.class), examples = @ExampleObject(value = "{\"message\": \"You are not authorized to access this resource.\", \"errorCode\": 1003, \"path\": \"/users\", \"timeStamp\": \"2024-12-17T14:45:58.597Z\"}"))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = BussinessErrorResponseDTO.class), examples = @ExampleObject(value = "{\"message\": \"You don't have permission to access this resource.\", \"errorCode\": 1004, \"path\": \"/users\", \"timeStamp\": \"2024-12-17T14:45:58.597Z\"}"))),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BussinessErrorResponseDTO.class), examples = {
            @ExampleObject(value = "{\"message\": \"Entity not found.\", \"errorCode\": 1000, \"path\": \"/users\", \"timeStamp\": \"2024-12-17T14:45:58.597Z\"}"),
            @ExampleObject(value = "{\"message\": \"Resource not found.\", \"errorCode\": 1005, \"path\": \"/users\", \"timeStamp\": \"2024-12-17T14:45:58.597Z\"}")
        })),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = BussinessErrorResponseDTO.class), examples = @ExampleObject(value = "{\"message\": \"An unexpected error has ocurred in the application.\", \"errorCode\": 1002, \"path\": \"/users\", \"timeStamp\": \"2024-12-17T14:45:58.597Z\"}")))
    }
)
@EnableSpringDataWebSupport( pageSerializationMode = PageSerializationMode.VIA_DTO )
public abstract class CrudControllerAbstractImpl<DTO, ID> implements CrudController<ID, DTO> {

    private final CrudService<ID, DTO> crudService;

    protected CrudControllerAbstractImpl(CrudService<ID, DTO> crudService) {
        this.crudService = crudService;
    }

    @Override
    @Operation(summary = "Get all entities.", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @GetMapping
    public Page<DTO> getAll(@RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return this.crudService.getAll(pageNumber, pageSize);
    }

    @Override
    @Operation(summary = "Get one entity.", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @GetMapping("/{id}")
    public DTO getOne(@PathVariable ID id) {
        return this.crudService.getOne(id);
    }

    @Override
    @Operation(summary = "Create one entity.", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @PostMapping
    public DTO create(@RequestBody @Valid DTO dto) {
        return this.crudService.create(dto);
    }

    @Override
    @Operation(summary = "Modifiy an entity with the given ID.", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @PutMapping()
    public DTO update(@RequestBody @Valid DTO dto) {
        return this.crudService.update(dto);
    }

    @Override
    @Operation(summary = "Delete an entity with the given ID.", security = @SecurityRequirement(name = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        this.crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

}