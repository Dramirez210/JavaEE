package mx.uacm.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.uacm.hibernateapp.entity.Cliente;
import mx.uacm.hibernateapp.entity.Factura;
import mx.uacm.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {

            em.getTransaction().begin();

            Cliente cliente = new Cliente("Cristian", "Castro");
            cliente.setFormaPago("paypal");

            Factura f1 = new Factura("Compras de supermercado", 5000L);
            Factura f2 = new Factura("Compras de tecnologia", 7000L);
            cliente.addFactura(f1)
                    .addFactura(f2);

            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println(cliente);

            em.getTransaction().begin();
            // Factura f3 = em.find(Factura.class, 1L);
            Factura f3 = new Factura("Compras de supermercado", 5000L);
            f3.setId(1L);
            cliente.removeFactura(f3);
            em.getTransaction().commit();
            System.out.println(cliente);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}