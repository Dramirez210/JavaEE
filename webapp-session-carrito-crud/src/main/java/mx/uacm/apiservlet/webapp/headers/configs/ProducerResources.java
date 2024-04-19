package mx.uacm.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {
    @Inject
    private Logger log;
    @Resource(name="jdbc/TestDB")
    private DataSource ds;
    @Produces
    @RequestScoped
    //named("conn")
    @MySQLConn
    private Connection beanConnection() throws NamingException, SQLException {
        //Context initContext = new InitialContext();
        //Context envContext  = (Context)initContext.lookup("java:/comp/env");
        //DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB"); //context.xml
        return ds.getConnection();
    }
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    public void close(@Disposes @MySQLConn Connection conn) throws SQLException {
        conn.close();
        log.info("Cerrando conexion mysql");
    }


}
