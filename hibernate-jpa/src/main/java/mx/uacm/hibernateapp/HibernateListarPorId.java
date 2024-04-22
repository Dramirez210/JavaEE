package mx.uacm.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.uacm.hibernateapp.entity.Cliente;
import mx.uacm.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateListarPorId {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        //Select con where y registros unicos
        /* Query query = em.createQuery("select c FROM Cliente c where c.formaPago=?1", Cliente.class);
        query.setParameter(1,"debito");
        Cliente c = (Cliente) query.getSingleResult();
        System.out.println(c);
        em.close(); */

        //select a tabla
        /*List<Cliente> clientes =  em.createQuery("select c FROM Cliente c", Cliente.class).getResultList();
        clientes.forEach(c -> System.out.println(c));
        clientes.forEach(System.out::println);
        em.close();*/

        //Dinamico
        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese id");
        Long id = s.nextLong();
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);
        em.close();
    }
}
