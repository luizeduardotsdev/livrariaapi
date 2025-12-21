package io.github.luizeduardotsdev.livrariaapi.repository;

import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import io.github.luizeduardotsdev.livrariaapi.model.GeneroLivro;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setDataPublicacao(LocalDate.of(1991, 1, 2));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setIsbn("2300-1111");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setTitulo("Teste outro livro");


        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasil");
        autor.setDataNascimento(LocalDate.of(1945, 11, 2));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("13bdf467-4c4e-46e7-952d-3bd36ac31f36");
        var livroParaAtualizar = livroRepository.findById(id)
                .orElse(null);

        UUID idAutor = UUID.fromString("08237797-7033-45f6-baa3-4957314f61ae");
        var luiz = autorRepository.findById(idAutor)
                .orElse(null);

        livroParaAtualizar.setAutor(luiz);

        livroRepository.save(livroParaAtualizar);

    }

    @Test
    public void deletarTest() {
        UUID id = UUID.fromString("6492b9aa-0fcb-4b29-8ece-f03256cf7757");
        livroRepository.deleteById(id);
    }

    @Test
    public void pesquisaTest(){
        List<Livro> lista = livroRepository.findByTitulo("13bdf467-4c4e-46e7-952d-3bd36ac31f36");
        lista.forEach(System.out::println);
    }
}