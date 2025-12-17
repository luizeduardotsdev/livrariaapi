package io.github.luizeduardotsdev.livrariaapi.repository;

import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

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
