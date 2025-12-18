package io.github.luizeduardotsdev.livrariaapi.repository;

import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import io.github.luizeduardotsdev.livrariaapi.model.GeneroLivro;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Livro livro = new Livro();

        livro.setDataPublicacao(LocalDate.of(1990, 1, 2));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setIsbn("9900-0991");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setTitulo("Teste ficcao");

        Autor autor = autorRepository.findById(UUID.fromString("08237797-7033-45f6-baa3-4957314f61ae"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }
}