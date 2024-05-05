package mx.uacm.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import mx.uacm.apiservlet.webapp.headers.configs.MySQLConn;
import mx.uacm.apiservlet.webapp.headers.configs.Repository;
import mx.uacm.apiservlet.webapp.headers.models.entities.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RepositoryJdbc
public class CaterogiaRepositoryImpl implements CrudRepository<Categoria> {

    private Connection conn;

    @Inject //Inyeccion via constructor
    public CaterogiaRepositoryImpl(@MySQLConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement(); //No tiene parametros
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias")){
            while(rs.next()){
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM categorias WHERE id =?")){ //Con parametros
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }


    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong( "id"));
        categoria.setNombre(rs.getString("nombre"));
        return categoria;
    }
}
