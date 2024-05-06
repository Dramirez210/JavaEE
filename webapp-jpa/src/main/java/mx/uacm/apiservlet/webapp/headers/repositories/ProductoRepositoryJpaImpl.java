package mx.uacm.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import mx.uacm.apiservlet.webapp.headers.configs.Repository;
import mx.uacm.apiservlet.webapp.headers.models.entities.Producto;

import java.util.List;

@RepositoryJpa
@Repository
public class ProductoRepositoryJpaImpl implements CrudRepository<Producto>{

    @Inject
    private EntityManager em;
    @Override
    public List<Producto> listar() throws Exception {
//        return em.createQuery("select p from Producto p", Producto.class).getResultList();
        return em.createQuery("select p from Producto p left outer join fetch p.categoria", Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) throws Exception {
        return em.find(Producto.class, id);
    }

    @Override
    public void guardar(Producto producto) throws Exception {
        if(producto.getId() != null && producto.getId() > 0){
            em.merge(producto);
        }else {
            em.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }
}