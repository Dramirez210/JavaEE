package edu.uacm.java.jdbc;

import edu.uacm.java.jdbc.modelo.Producto;
import edu.uacm.java.jdbc.repositorio.ProductoRepositorioImpl;
import edu.uacm.java.jdbc.repositorio.Repositorio;
import edu.uacm.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class MainUpdate {
    public static void main(String[] args) {

        String sql = "SELECT * FROM productos";
        try (Connection con = ConexionBaseDatos.getInstance())  { //Autoclose
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

            System.out.println("Metodo Listar:");
            //repositorio.listar().forEach(p -> System.out.println(p));
            repositorio.listar().forEach(System.out::println);

            System.out.println("Buscar metodo porId: \n" + repositorio.porId(1L));

            System.out.println("Metodo Actualizar");
            Producto pro = new Producto();
            pro.setId(3L);
            pro.setNombre("Teclado Microsoft");
            pro.setPrecio(148.5);
            repositorio.guardar(pro);
            repositorio.listar().forEach(System.out::println);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}