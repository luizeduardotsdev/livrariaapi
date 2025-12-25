package io.github.luizeduardotsdev.livrariaapi.controller.dto;

import io.github.luizeduardotsdev.livrariaapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroDTO(

        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        UUID idAutor
        
        ) {
}
