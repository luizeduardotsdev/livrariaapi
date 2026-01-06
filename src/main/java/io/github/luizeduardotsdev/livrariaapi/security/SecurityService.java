package io.github.luizeduardotsdev.livrariaapi.security;

import io.github.luizeduardotsdev.livrariaapi.model.Usuario;
import io.github.luizeduardotsdev.livrariaapi.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public Usuario obterUsuarioLogado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof CustomAuthentication customAuthentication) {
            return customAuthentication.getUsuario();
        }
        return null;
    }
}
