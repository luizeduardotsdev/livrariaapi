package io.github.luizeduardotsdev.livrariaapi.controller;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.UsuarioDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.mapper.UsuarioMapper;
import io.github.luizeduardotsdev.livrariaapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO usuarioDTO) {
        var usuario = usuarioMapper.toEntity(usuarioDTO);
        usuarioService.salvar(usuario);
    }
}
