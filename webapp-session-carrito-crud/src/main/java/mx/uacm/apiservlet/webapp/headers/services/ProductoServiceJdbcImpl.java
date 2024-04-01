package mx.uacm.apiservlet.webapp.headers.services;

import mx.uacm.apiservlet.webapp.headers.models.Categoria;
import mx.uacm.apiservlet.webapp.headers.models.Producto;
import mx.uacm.apiservlet.webapp.headers.repositories.CaterogiaRepositoryImpl;
import mx.uacm.apiservlet.webapp.headers.repositories.ProductoRepositoryJdbcImpl;
import mx.uacm.apiservlet.webapp.headers.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService{
    private Repository<Producto> repositoryProductoJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public ProductoServiceJdbcImpl(Connection connection) {
        this.repositoryProductoJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CaterogiaRepositoryImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryProductoJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause()); //excepcion personalizada
        }
    }

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
