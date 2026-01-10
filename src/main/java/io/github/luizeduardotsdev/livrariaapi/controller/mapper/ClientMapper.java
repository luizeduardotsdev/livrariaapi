package io.github.luizeduardotsdev.livrariaapi.controller.mapper;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.ClientDTO;
import io.github.luizeduardotsdev.livrariaapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO clientDTO);
}
