package io.github.luizeduardotsdev.livrariaapi.service;

import io.github.luizeduardotsdev.livrariaapi.model.Usuario;
import io.github.luizeduardotsdev.livrariaapi.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void salvar(Usuario usuario) {
        var senha = usuario.getSenha();
        usuario.setSenha(passwordEncoder.encode(senha));

        usuarioRepository.save(usuario);
    }

    public Usuario obetPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public Usuario obterPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
