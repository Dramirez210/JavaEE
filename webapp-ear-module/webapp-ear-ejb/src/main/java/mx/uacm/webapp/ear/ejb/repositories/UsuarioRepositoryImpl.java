package mx.uacm.webapp.ear.ejb.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import mx.uacm.webapp.ear.ejb.entities.Usuario;

import java.util.List;

//@RequestScoped
@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository{
    @Inject
    private EntityManager em;

    @Override
    public List<Usuario> listar() {
        return em.createQuery("from Usuario", Usuario.class).getResultList();
    }
}
