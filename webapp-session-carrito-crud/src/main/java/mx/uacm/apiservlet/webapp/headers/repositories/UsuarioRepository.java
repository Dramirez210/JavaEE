package mx.uacm.apiservlet.webapp.headers.repositories;

import mx.uacm.apiservlet.webapp.headers.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario>{
    Usuario porUsername(String username) throws SQLException;
}
