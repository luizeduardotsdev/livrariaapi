package io.github.luizeduardotsdev.livrariaapi.controller;

import io.github.luizeduardotsdev.livrariaapi.UriController;
import io.github.luizeduardotsdev.livrariaapi.controller.dto.CadastroLivroDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.dto.ErroResposta;
import io.github.luizeduardotsdev.livrariaapi.controller.mapper.LivroMapper;
import io.github.luizeduardotsdev.livrariaapi.exceptions.RegistroDuplicadoException;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import io.github.luizeduardotsdev.livrariaapi.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController implements UriController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    public LivroController(LivroService livroService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        try {
            Livro livro = livroMapper.toEntity(dto);
            livroService.salvar(livro);

            var url = gerarHeaderLocation(livro.getId());

            return ResponseEntity.created(url).build();
        }catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
