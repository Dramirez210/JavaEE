package mx.uacm.apiservlet.webapp.headers.services;

import mx.uacm.apiservlet.webapp.headers.models.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
    void guardar(Usuario usuario);
    Optional<Usuario> porId(Long id);
}
