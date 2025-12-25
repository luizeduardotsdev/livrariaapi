package io.github.luizeduardotsdev.livrariaapi.validator;

import io.github.luizeduardotsdev.livrariaapi.exceptions.RegistroDuplicadoException;
import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import io.github.luizeduardotsdev.livrariaapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository autorRepository;

    public AutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void validar(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("autor ja cadastrado");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(),autor.getDataNascimento(), autor.getNacionalidade());

        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();

    }
}
