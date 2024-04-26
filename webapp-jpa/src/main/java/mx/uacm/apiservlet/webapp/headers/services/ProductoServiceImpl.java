package mx.uacm.apiservlet.webapp.headers.services;

import jakarta.inject.Inject;
import mx.uacm.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import mx.uacm.apiservlet.webapp.headers.configs.Service;
import mx.uacm.apiservlet.webapp.headers.interceptors.TransactionalJpa;
import mx.uacm.apiservlet.webapp.headers.models.entities.Categoria;
import mx.uacm.apiservlet.webapp.headers.models.entities.Producto;
import mx.uacm.apiservlet.webapp.headers.repositories.CrudRepository;
import mx.uacm.apiservlet.webapp.headers.repositories.RepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
@TransactionalJpa
public class ProductoServiceImpl implements ProductoService{
    @Inject
    @RepositoryJpa
    private CrudRepository<Producto> repositoryProductoJdbc;

    @Inject
    @RepositoryJpa
    private CrudRepository<Categoria> repositoryCategoriaJdbc;
    @Override
    public List<Producto> listar() {
        try {
            return repositoryProductoJdbc.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause()); //excepcion personalizada
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryProductoJdbc.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryProductoJdbc.guardar(producto);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryProductoJdbc.eliminar(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
