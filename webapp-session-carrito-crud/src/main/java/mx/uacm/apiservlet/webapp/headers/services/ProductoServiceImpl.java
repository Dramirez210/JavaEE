package mx.uacm.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import mx.uacm.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import mx.uacm.apiservlet.webapp.headers.models.Categoria;
import mx.uacm.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Alternative
//@ApplicationScoped
//@ProductoServicePrincipal
public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000d),
                new Producto(2L, "mesa escritorio", "oficina", 100000D),
                new Producto(3L, "teclado mecanico", "computacion", 40000d));
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategoria() {
        return null;
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }
}
