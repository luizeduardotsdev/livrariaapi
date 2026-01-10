package io.github.luizeduardotsdev.livrariaapi.service;

import io.github.luizeduardotsdev.livrariaapi.model.Client;
import io.github.luizeduardotsdev.livrariaapi.repository.ClientRespository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {

    private final ClientRespository clientRespository;

    public ClientService(ClientRespository clientRespository) {
        this.clientRespository = clientRespository;
    }

    public Client salvar(Client client){
        return clientRespository.save(client);
    }

    public Client obterPorClientId(String id){
        return clientRespository.findByClientID(id);
    }
}
