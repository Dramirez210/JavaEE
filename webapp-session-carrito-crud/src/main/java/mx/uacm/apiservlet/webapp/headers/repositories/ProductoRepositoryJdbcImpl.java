package mx.uacm.apiservlet.webapp.headers.repositories;

import mx.uacm.apiservlet.webapp.headers.models.Categoria;
import mx.uacm.apiservlet.webapp.headers.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto>{
    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos AS p" +
                    " INNER JOIN categorias AS c ON (p.categoria_id = c.idcategorias) ORDER BY p.idProductos ASC")){
            while(rs.next()){
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement ps = conn.prepareStatement("SELECT p.*, c.nombre AS categoria FROM productos AS p" +
                " INNER JOIN categorias AS c ON (p.categoria_id = c.idcategorias) WHERE p.idproductos = ?")){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if(producto.getId() != null && producto.getId() > 0){
            sql = "UPDATE productos set nombre=?, precio=?, sku=?, categoria_id=? WHERE idproductos=?";
        }else{
            sql ="INSERT INTO productos (nombre, precio, sku, categoria_id, fecha_registro)" +
                    " VALUES(?,?,?,?,?)";
        }
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getSku());
            ps.setLong(4, producto.getCategoria().getId());
            if(producto.getId() != null && producto.getId() > 0){
                ps.setLong(5, producto.getId());
            }else {
                ps.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from productos where idProductos=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("idProductos"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);

        return p;
    }
}
