package io.github.luizeduardotsdev.livrariaapi.controller;

import java.time.LocalDate;

public record AutorDTO(String nome,
                       LocalDate dataNascimento,
                       String Nacionalidade) {
}
