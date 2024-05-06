package mx.uacm.webapp.jfs3.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import mx.uacm.webapp.jfs3.entities.Producto;
import mx.uacm.webapp.jfs3.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private CrudRepository<Producto> repository;

    @Override
    public List<Producto> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }
}
