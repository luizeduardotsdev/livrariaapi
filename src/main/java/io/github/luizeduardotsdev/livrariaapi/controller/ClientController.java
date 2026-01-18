package io.github.luizeduardotsdev.livrariaapi.controller;

import io.github.luizeduardotsdev.livrariaapi.controller.dto.ClientDTO;
import io.github.luizeduardotsdev.livrariaapi.controller.mapper.ClientMapper;
import io.github.luizeduardotsdev.livrariaapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client")
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
    @Operation(summary = "Salvar", description = "Cadastra um novo Client.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client cadastrado.")
    })
    public void salvar(@RequestBody ClientDTO clientDTO){
        var client = clientMapper.toEntity(clientDTO);
        clientService.salvar(client);
    }
}
