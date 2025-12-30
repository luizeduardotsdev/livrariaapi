package io.github.luizeduardotsdev.livrariaapi.controller;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.CadastroLivroDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.mapper.LivroMapper;
import io.github.luizeduardotsdev.livrariaapi.model.GeneroLivro;
import io.github.luizeduardotsdev.livrariaapi.model.Livro;
import io.github.luizeduardotsdev.livrariaapi.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<ResultadoPesquisaLivroDTO>> pesquisa(
            @RequestParam(value = "isbn", required = false)
            String isbn,

            @RequestParam(value = "titulo", required = false)
            String titulo,

            @RequestParam(value = "nomeAutor", required = false)
            String nomeAutor,

            @RequestParam(value = "genero", required = false)
            GeneroLivro genero,

            @RequestParam(value = "anoPublicacao", required = false)
            Integer anoPublicacao
    ) {
        var resultado = livroService.pesquisa(isbn, titulo, nomeAutor, genero, anoPublicacao);

        var lista = resultado.stream().map(livroMapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody @Valid CadastroLivroDTO dto){

        return livroService.obterPorID(UUID.fromString(id)).map(livro -> {
            Livro entidadeAux = livroMapper.toEntity(dto);
            livro.setDataPublicacao(entidadeAux.getDataPublicacao());
            livro.setIsbn(entidadeAux.getIsbn());
            livro.setPreco(entidadeAux.getPreco());
            livro.setGenero(entidadeAux.getGenero());
            livro.setTitulo(entidadeAux.getTitulo());
            livro.setAutor(entidadeAux.getAutor());

            livroService.atualizar(livro);
            return ResponseEntity.noContent().build();

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
