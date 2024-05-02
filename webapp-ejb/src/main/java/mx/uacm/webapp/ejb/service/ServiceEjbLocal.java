package mx.uacm.webapp.ejb.service;

import jakarta.ejb.Local;
import mx.uacm.webapp.ejb.models.Producto;

import java.util.List;

@Local
public interface ServiceEjbLocal {
    String saludar(String nombre);
    List<Producto> listar();
    Producto crear(Producto producto);
}
