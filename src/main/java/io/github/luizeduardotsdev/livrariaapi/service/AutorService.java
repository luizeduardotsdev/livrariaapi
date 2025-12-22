package io.github.luizeduardotsdev.livrariaapi.service;

import io.github.luizeduardotsdev.livrariaapi.model.Autor;
import io.github.luizeduardotsdev.livrariaapi.repository.AutorRepository;
import io.github.luizeduardotsdev.livrariaapi.validator.AutorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;

    public AutorService(AutorRepository autorRepository, AutorValidator autorValidator) {
        this.autorRepository = autorRepository;
        this.autorValidator = autorValidator;
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor) {
        if (autor.getId() == null ){
            throw new IllegalArgumentException("Para atualziar é neccesario que o autor exista");
        }
        autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor) {
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisar(String nome, String nacionalidade) {
        if (nome != null && nacionalidade != null) {
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null) {
            return autorRepository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();

    }
}
