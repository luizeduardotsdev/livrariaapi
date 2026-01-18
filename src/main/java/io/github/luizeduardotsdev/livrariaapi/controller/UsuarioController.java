package io.github.luizeduardotsdev.livrariaapi.controller;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.UsuarioDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.mapper.UsuarioMapper;
import io.github.luizeduardotsdev.livrariaapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar", description = "Cadastra um novo usuario.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario cadastrado.")
    })
    public void salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        var usuario = usuarioMapper.toEntity(usuarioDTO);
        usuarioService.salvar(usuario);
    }
}
