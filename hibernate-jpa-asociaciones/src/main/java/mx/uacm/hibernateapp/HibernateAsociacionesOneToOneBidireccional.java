package mx.uacm.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.uacm.hibernateapp.entity.Cliente;
import mx.uacm.hibernateapp.entity.ClienteDetalle;
import mx.uacm.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOneBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Cristian", "Castro");
            cliente.setFormaPago("paypal");

            ClienteDetalle detalle = new ClienteDetalle(true, 8000L);

            cliente.addDetalle(detalle);

            em.persist(cliente);
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
