package mx.uacm.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Named
@RequestScoped
@Stereotype
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Repository {
}
