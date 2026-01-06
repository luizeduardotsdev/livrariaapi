package io.github.luizeduardotsdev.livrariaapi.security;

import io.github.luizeduardotsdev.livrariaapi.model.Usuario;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class CustomAuthetication implements Authentication {

    private final Usuario usuario;

    public CustomAuthetication(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.usuario
                .getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable Object getCredentials() {
        return null;
    }

    @Override
    public @Nullable Object getDetails() {
        return usuario;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return usuario;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return usuario.getLogin();
    }
}
