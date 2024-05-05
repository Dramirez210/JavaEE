package mx.uacm.webapp.ear.ejb.repositories;

import mx.uacm.webapp.ear.ejb.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {
    List<Usuario> listar();
}
