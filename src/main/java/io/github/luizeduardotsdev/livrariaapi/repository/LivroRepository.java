package io.github.luizeduardotsdev.livrariaapi.repository;

import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {


    // select * from livro where livro = livro
    List<Livro> findByTitulo(String titulo);

    // select * from livro where data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);
}
