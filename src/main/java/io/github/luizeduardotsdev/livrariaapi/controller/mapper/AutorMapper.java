package io.github.luizeduardotsdev.livrariaapi.controller.mapper;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.AutorDTO;
import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
