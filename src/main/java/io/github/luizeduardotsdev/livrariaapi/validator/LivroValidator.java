package io.github.luizeduardotsdev.livrariaapi.validator;

import io.github.luizeduardotsdev.livrariaapi.exceptions.CampoInvalidoException;
import io.github.luizeduardotsdev.livrariaapi.exceptions.RegistroDuplicadoException;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import io.github.luizeduardotsdev.livrariaapi.repository.LivroRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LivroValidator {

    public static final int ANO_EXIGENCIA_PRECO = 2020;

    private LivroRepository livroRepository;

    public LivroValidator(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void validarLivro(Livro livro) {
        if (existeLivroComIsbn(livro)) {
            throw new RegistroDuplicadoException("ISBN já cadastrado!");
        }

        if (isPrecoObrigatorioNulo(livro)) {
            throw new CampoInvalidoException("preco", "Para livros com ano de publicação a partir de 2020, o preço é obrigatorio");
        }
    }

    private boolean isPrecoObrigatorioNulo(Livro livro) {

        return livro.getPreco() == null && livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO;
    }

    private boolean existeLivroComIsbn(Livro livro) {
        Optional<Livro> livroEncotrado = livroRepository.findByIsbn(livro.getIsbn());

        if (livro.getId() == null) {
            return livroEncotrado.isPresent();
        }

        return livroEncotrado
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));
    }
}
