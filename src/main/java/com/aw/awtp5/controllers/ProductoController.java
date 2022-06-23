package com.aw.awtp5.controllers;

import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService service;

    @PostMapping
    public Producto save(@RequestBody Producto producto)  throws Throwable {
        return this.service.save(producto);
    }

    @GetMapping
    public List<Producto> getAll() throws Throwable {
        return this.service.getAll();
    }

    @DeleteMapping
    public void delete(@RequestBody Producto p) throws Throwable {
        this.service.delete(p);
    }

    @PutMapping
    public Producto update(@RequestBody Producto producto)  throws Throwable {
        return this.service.save(producto);
    }

}
