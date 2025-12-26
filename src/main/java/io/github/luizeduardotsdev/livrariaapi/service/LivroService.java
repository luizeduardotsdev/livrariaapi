package io.github.luizeduardotsdev.livrariaapi.service;

import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import io.github.luizeduardotsdev.livrariaapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorID(UUID id){
        return livroRepository.findById(id);
    }
}
