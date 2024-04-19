package mx.uacm.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mx.uacm.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import mx.uacm.apiservlet.webapp.headers.configs.Service;
import mx.uacm.apiservlet.webapp.headers.interceptors.Logging;
import mx.uacm.apiservlet.webapp.headers.models.Categoria;
import mx.uacm.apiservlet.webapp.headers.models.Producto;
import mx.uacm.apiservlet.webapp.headers.repositories.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
public class ProductoServiceJdbcImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        try {
            return repositoryProductoJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause()); //excepcion personalizada
        }
    }
    @Inject
    private CrudRepository<Producto> repositoryProductoJdbc;

    @Inject
    private CrudRepository<Categoria> repositoryCategoriaJdbc;

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryProductoJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryProductoJdbc.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryProductoJdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
