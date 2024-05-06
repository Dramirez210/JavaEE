package mx.uacm.webapp.jfs3.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mx.uacm.webapp.jfs3.entities.Producto;
import mx.uacm.webapp.jfs3.service.ProductoService;

import java.util.List;

@Model
public class ProductoController {

    @Inject
    private ProductoService service;

    @Produces
    @Model
    public String titulo() {
        return "Hola mundo JavaServer Face 3.0";
    }

    @Produces
    @RequestScoped
    @Named("listado")
    public List<Producto> findAll() {
        return service.listar();
    }
}
