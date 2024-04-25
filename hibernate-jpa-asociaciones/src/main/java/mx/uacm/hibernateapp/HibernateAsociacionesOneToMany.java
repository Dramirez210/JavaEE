package mx.uacm.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.uacm.hibernateapp.entity.Cliente;
import mx.uacm.hibernateapp.entity.Direccion;
import mx.uacm.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Cristian", "Castro");
            cliente.setFormaPago("mercado pago");

            Direccion d1 = new Direccion("Av Camarones", 13);
            Direccion d2 = new Direccion("Vasco de quiroga ", 56);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);
            em.persist(cliente);

            em.getTransaction().commit();

            System.out.println(cliente);

            em.getTransaction().begin();
            cliente = em.find(Cliente.class, cliente.getId());
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
