package mx.uacm.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.uacm.apiservlet.webapp.headers.models.entities.Usuario;
import mx.uacm.apiservlet.webapp.headers.services.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {
    @Inject
    UsuarioService serviceUsuario;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        Usuario usuario = new Usuario();
        if (id > 0){
            Optional<Usuario> o = serviceUsuario.porId(id);
            if(o.isPresent()){
                usuario = o.get();
            }
        }

        req.setAttribute("title", req.getAttribute("title") + ": Creacion de Usuarios");
        req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/formUsuarios.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String passwordConfirmacion = req.getParameter("password2");
        String email = req.getParameter("email");

        Map<String, String> errores = new HashMap<>();

        if(username == null || username.isEmpty()){
            errores.put("username", "Campo requerido");
        }

        if(password == null || password.isEmpty()){
            errores.put("password", "Campo requerido");
        }

        if(passwordConfirmacion == null || passwordConfirmacion.isEmpty()){
            errores.put("password2", "Campo requerido");
        }else if(!password.equals(passwordConfirmacion)) {
            errores.put("password2","No coinciden las credenciales");
        }

        if(email == null || email.isEmpty()){
            errores.put("email", "Campo requerido");
        }

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);
        if(errores.isEmpty()){
            serviceUsuario.guardar(usuario);
            resp.sendRedirect(req.getContextPath() + "/");
        }else {
            req.setAttribute("errores", errores);
            req.setAttribute("usuario", usuario);
            req.setAttribute("title", req.getAttribute("title") + ": Creación de Usuario");
            getServletContext().getRequestDispatcher("/formUsuarios.jsp").forward(req, resp);
        }
    }
}
