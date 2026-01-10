package io.github.luizeduardotsdev.livrariaapi.service;

import io.github.luizeduardotsdev.livrariaapi.model.Client;
import io.github.luizeduardotsdev.livrariaapi.repository.ClientRespository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRespository clientRespository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRespository clientRespository, PasswordEncoder passwordEncoder) {
        this.clientRespository = clientRespository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client salvar(Client client){
        var senhaCriptograda = passwordEncoder.encode(client.getClientSecret());
        client.setClientSecret(senhaCriptograda);
        return clientRespository.save(client);
    }

    public Client obterPorClientId(String id){
        return clientRespository.findByClientId(id);
    }
}
