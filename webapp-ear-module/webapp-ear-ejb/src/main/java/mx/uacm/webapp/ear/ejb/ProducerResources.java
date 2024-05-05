package mx.uacm.webapp.ear.ejb;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class ProducerResources {

    @PersistenceUnit(name = "ejemploJpa")
    private EntityManagerFactory emf;

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager(){
        return emf.createEntityManager();
    }

    private void close(@Disposes EntityManager entityManager){
        if(entityManager.isOpen()){
            entityManager.close();
            System.out.println("Cerrando conexion EM");
        }
    }
}
