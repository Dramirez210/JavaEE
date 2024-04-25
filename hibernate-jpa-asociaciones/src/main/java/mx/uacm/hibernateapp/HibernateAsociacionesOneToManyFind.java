package mx.uacm.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.uacm.hibernateapp.entity.Cliente;
import mx.uacm.hibernateapp.entity.Direccion;
import mx.uacm.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 2L);

            Direccion d1 = new Direccion("Av camarones", 13);
            Direccion d2 = new Direccion("Vasco de quiroga", 56);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);
            em.merge(cliente);

            em.getTransaction().commit();

            System.out.println(cliente);

            em.getTransaction().begin();
            d1 = em.find(Direccion.class, 1L);
            cliente.getDirecciones().remove(d1);
            em.getTransaction().commit();
            System.out.println(cliente);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
