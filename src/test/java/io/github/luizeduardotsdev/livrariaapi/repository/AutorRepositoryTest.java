package io.github.luizeduardotsdev.livrariaapi.repository;

import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
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

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("0x08237797703345F6BAA34957314F61AE");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: " + autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1988, 11, 1));

            autorRepository.save(autorEncontrado);
        }
    }


    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletarAutorPorId() {
        var id = UUID.fromString( "0x08237797703345F6BAA34957314F61AE");
        autorRepository.deleteById(id);
    }
}
