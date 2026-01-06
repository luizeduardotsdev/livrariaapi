package io.github.luizeduardotsdev.livrariaapi.controller.mapper;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.UsuarioDTO;
import io.github.luizeduardotsdev.livrariaapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

}
