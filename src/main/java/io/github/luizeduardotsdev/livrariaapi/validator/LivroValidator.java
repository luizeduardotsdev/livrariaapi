package io.github.luizeduardotsdev.livrariaapi.validator;

import io.github.luizeduardotsdev.livrariaapi.exceptions.RegistroDuplicadoException;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import io.github.luizeduardotsdev.livrariaapi.repository.LivroRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LivroValidator {

    private LivroRepository livroRepository;

    public LivroValidator(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void validarLivro(Livro livro) {
        if (existeLivroComIsbn(livro)) {
            throw new RegistroDuplicadoException("ISBN já cadastrado!");
        }
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
