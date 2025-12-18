package io.github.luizeduardotsdev.livrariaapi.repository;

import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import io.github.luizeduardotsdev.livrariaapi.model.GeneroLivro;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

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

    @Test
    public void SalvarAutorTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("EUA");
        autor.setDataNascimento(LocalDate.of(2000, 5, 18));

        Livro livro = new Livro();
        livro.setDataPublicacao(LocalDate.of(2022, 1, 2));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setIsbn("0044-4400");
        livro.setPreco(BigDecimal.valueOf(210));
        livro.setTitulo("It a coisa");
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setDataPublicacao(LocalDate.of(2025, 11, 2));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setIsbn("1144-4411");
        livro2.setPreco(BigDecimal.valueOf(210));
        livro2.setTitulo("welcome to derry");
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());

    }
}
