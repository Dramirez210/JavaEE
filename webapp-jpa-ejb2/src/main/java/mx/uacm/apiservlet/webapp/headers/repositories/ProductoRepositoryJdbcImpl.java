package mx.uacm.apiservlet.webapp.headers.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import mx.uacm.apiservlet.webapp.headers.configs.MySQLConn;
import mx.uacm.apiservlet.webapp.headers.configs.Repository;
import mx.uacm.apiservlet.webapp.headers.models.entities.Categoria;
import mx.uacm.apiservlet.webapp.headers.models.entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
@RepositoryJdbc
public class ProductoRepositoryJdbcImpl implements CrudRepository<Producto> {
    @Inject
    private Logger log;
    @Inject
    //@Named("conn")
    @MySQLConn
    private Connection conn;
    @PostConstruct
    public void inicializar(){
        log.info("Inicializando beans " + this.getClass().getName());
    }
    @PreDestroy
    public void destruir(){
        log.info("Destruyendo el beans "+this.getClass().getName());
    }
    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos AS p" +
                    " INNER JOIN categorias AS c ON (p.categoria_id = c.id) ORDER BY p.id ASC")){
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
                " INNER JOIN categorias AS c ON (p.categoria_id = c.id) WHERE p.id = ?")){
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
            sql = "UPDATE productos set nombre=?, precio=?, sku=?, categoria_id=? WHERE id=?";
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
        String sql = "delete from productos where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
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
