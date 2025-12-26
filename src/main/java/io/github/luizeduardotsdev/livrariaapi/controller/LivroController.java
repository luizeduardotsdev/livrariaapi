package io.github.luizeduardotsdev.livrariaapi.controller;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.CadastroLivroDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.mapper.LivroMapper;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import io.github.luizeduardotsdev.livrariaapi.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        Livro livro = livroMapper.toEntity(dto);
        livroService.salvar(livro);

        var url = gerarHeaderLocation(livro.getId());

        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("id") String id){

        return livroService.obterPorID(UUID.fromString(id)).map(livro -> {
            var dto = livroMapper.toDTO(livro);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id) {
        return livroService.obterPorID(UUID.fromString(id)).map(livro -> {
            livroService.deletar(livro);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
