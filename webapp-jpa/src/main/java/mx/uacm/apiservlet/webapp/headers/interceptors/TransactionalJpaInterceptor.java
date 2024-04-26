package mx.uacm.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import mx.uacm.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {
    @Inject
    private EntityManager em;
    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        try {
            log.info(" ##### Iniciando transaccion JPA" +invocationContext.getMethod().getName() +
                    " de la clase " + invocationContext.getMethod().getDeclaringClass());
            em.getTransaction().begin();
            Object resultado = invocationContext.proceed();
            em.getTransaction().commit();
            log.info(" #####  commit y finalizacion de transaccion JPA " +invocationContext.getMethod().getName() +
                    " de la clase " + invocationContext.getMethod().getDeclaringClass());
            return resultado;
        }catch (ServiceJdbcException e){
            em.getTransaction().rollback();
            throw e;
        }
    }
}
