package mx.uacm.webapp.ejb.service;

import jakarta.ejb.Remote;
import mx.uacm.webapp.ejb.models.Producto;

import java.util.List;

@Remote
public interface ServiceEjbRemote {
    String saludar(String nombre);
    List<Producto> listar();
    Producto crear(Producto producto);
}
