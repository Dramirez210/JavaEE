package mx.uacm.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import mx.uacm.apiservlet.webapp.headers.configs.MySQLConn;
import mx.uacm.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {
    @Inject
    @MySQLConn
    private Connection conn;
    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        if(conn.getAutoCommit()){
            conn.setAutoCommit(false);
        }
        try {
            log.info(" ##### Iniciando transaccion " +invocationContext.getMethod().getName() +
                    " de la clase " + invocationContext.getMethod().getDeclaringClass());
            Object resultado = invocationContext.proceed();
            conn.commit();
            log.info(" #####  commit y finalizacion de transaccion " +invocationContext.getMethod().getName() +
                    " de la clase " + invocationContext.getMethod().getDeclaringClass());
            return resultado;
        }catch (ServiceJdbcException e){
            conn.rollback();
            throw e;
        }
    }
}
