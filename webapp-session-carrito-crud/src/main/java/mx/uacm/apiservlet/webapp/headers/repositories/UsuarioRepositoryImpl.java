package mx.uacm.apiservlet.webapp.headers.repositories;

import mx.uacm.apiservlet.webapp.headers.models.Producto;
import mx.uacm.apiservlet.webapp.headers.models.Usuario;

import java.sql.*;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository{

    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        return null;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id_usuarios = ?" ;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0) {
            sql = "UPDATE usuarios set username=?, password=?, email=? WHERE id_usuarios=?";
        } else {
            sql = "INSERT INTO usuarios (username, password, email) VALUES(?,?,?)";
        }
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getEmail());
            if (usuario.getId() != null && usuario.getId() > 0) {
                ps.setLong(4, usuario.getId());
            }
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from usuarios where username=?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id_usuarios"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }
}
