package mx.uacm.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.uacm.hibernateapp.entity.Cliente;
import mx.uacm.hibernateapp.entity.Factura;
import mx.uacm.hibernateapp.util.JpaUtil;


public class HibernateAsociacionesManyToOneFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 1L);

            Factura factura = new Factura("compras de oficina", 1000L);
            factura.setCliente(cliente);
            em.persist(factura);

            System.out.println(factura.getCliente());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
