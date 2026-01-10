package io.github.luizeduardotsdev.livrariaapi.controller;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.ClientDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.mapper.ClientMapper;
import io.github.luizeduardotsdev.livrariaapi.model.Client;
import io.github.luizeduardotsdev.livrariaapi.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public void salvar(@RequestBody ClientDTO clientDTO){
        var client = clientMapper.toEntity(clientDTO);
        clientService.salvar(client);
    }
}
