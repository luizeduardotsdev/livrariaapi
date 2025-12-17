package io.github.luizeduardotsdev.livrariaapi.repository;

import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class AutorRepositoryTest {

    AutorRepository autorRepository;

    @Test
    public void salvarTest () {
        Autor autor = new Autor();
        autor.setNome("luiz");
        autor.setNacionalidade("Brasil");
        autor.setDataNascimento(LocalDate.of(1955, 1, 2));

        var autorSalvo = autorRepository.save(autor);
        System.out.println(autor);
    }
}
