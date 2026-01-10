package io.github.luizeduardotsdev.livrariaapi.security;

import io.github.luizeduardotsdev.livrariaapi.config.AuthorizationServerConfiguration;
import io.github.luizeduardotsdev.livrariaapi.service.ClientService;
import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private final ClientService clientService;
    private final AuthorizationServerConfiguration authorizationServerConfiguration;

    public CustomRegisteredClientRepository(ClientService clientService, AuthorizationServerConfiguration authorizationServerConfiguration) {
        this.clientService = clientService;
        this.authorizationServerConfiguration = authorizationServerConfiguration;
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public @Nullable RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public @Nullable RegisteredClient findByClientId(String clientId) {
        var client = clientService.obterPorClientId(clientId);

        if (client == null) {
            return null;
        }
        return RegisteredClient.withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .redirectUri(client.getRedirectUri())
                .scope(client.getScope())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenSettings(authorizationServerConfiguration.tokenSettings())
                .clientSettings(authorizationServerConfiguration.clientSettings())
                .build();
    }
}
