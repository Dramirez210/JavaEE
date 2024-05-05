package mx.uacm.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LogginInterceptor {
    @Inject
    private Logger log;
    @AroundInvoke
    public Object logging(InvocationContext invocationContext) throws Exception {
        log.info(" ***** Antes de invocar el metodo " +invocationContext.getMethod().getName() + " de la clase " + invocationContext.getMethod().getDeclaringClass());
        Object resultado = invocationContext.proceed();
        log.info(" ***** Despues de invocar el metodo " +invocationContext.getMethod().getName());
        return resultado;
    }
}
