package edu.uacm.java.jdbc;

import edu.uacm.java.jdbc.modelo.Categoria;
import edu.uacm.java.jdbc.modelo.Producto;
import edu.uacm.java.jdbc.repositorio.ProductoRepositorioImpl;
import edu.uacm.java.jdbc.repositorio.Repositorio;
import edu.uacm.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        String sql = "SELECT * FROM productos";
        try (Connection con = ConexionBaseDatos.getInstance())  { //Autoclose
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

            System.out.println("Metodo Listar:");
            //repositorio.listar().forEach(p -> System.out.println(p));
            repositorio.listar().forEach(System.out::println);

            System.out.println("Buscar metodo porId: \n" + repositorio.porId(3L));

            System.out.println("Metodo Insertar");
            Producto pro = new Producto();
            pro.setNombre("Teclado qwerty 2000");
            pro.setPrecio(150d);
            pro.setFechaRegistro(new Date());

            Categoria categoria = new Categoria();
            categoria.setId(2L);

            pro.setCategoria(categoria);
            repositorio.guardar(pro);
            repositorio.listar().forEach(System.out::println);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}