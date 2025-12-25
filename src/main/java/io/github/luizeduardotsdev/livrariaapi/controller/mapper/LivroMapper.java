package io.github.luizeduardotsdev.livrariaapi.controller.mapper;


import io.github.luizeduardotsdev.livrariaapi.controller.dto.CadastroLivroDTO;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import io.github.luizeduardotsdev.livrariaapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( dto.idAutor() != null ? autorRepository.findById(dto.idAutor()).orElse(null) : null )")
    public abstract Livro toEntity(CadastroLivroDTO dto);
}
