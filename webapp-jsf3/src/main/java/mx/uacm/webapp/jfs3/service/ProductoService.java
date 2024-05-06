package mx.uacm.webapp.jfs3.service;

import jakarta.ejb.Local;
import mx.uacm.webapp.jfs3.entities.Producto;

import java.util.List;
import java.util.Optional;

@Local
public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Long id);
}
