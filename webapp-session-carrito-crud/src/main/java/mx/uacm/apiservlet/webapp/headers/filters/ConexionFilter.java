package mx.uacm.apiservlet.webapp.headers.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import mx.uacm.apiservlet.webapp.headers.configs.MySQLConn;
import mx.uacm.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    /*@Inject
    //@Named("conn")
    @MySQLConn
    private Connection conn;*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*try {
            Connection connRequest = this.conn;
            if(connRequest.getAutoCommit()){
                connRequest.setAutoCommit(false);
            }*/
            try{
                //servletRequest.setAttribute("conn", connRequest);
                filterChain.doFilter(servletRequest, servletResponse);
                //connRequest.commit();
            }catch (ServiceJdbcException e){ //puente
                //connRequest.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
                e.printStackTrace();
            }
        /*} catch (SQLException e ) {
            e.printStackTrace();
        }*/
    }
}
