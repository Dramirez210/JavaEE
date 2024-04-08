package mx.uacm.apiservlet.webapp.headers.services;

import mx.uacm.apiservlet.webapp.headers.models.Usuario;
import mx.uacm.apiservlet.webapp.headers.repositories.UsuarioRepository;
import mx.uacm.apiservlet.webapp.headers.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            usuarioRepository.guardar(usuario);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(usuarioRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
