package com.aw.awtp5.controllers;

import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public Producto findById(@PathVariable String id) throws Throwable {
        return this.service.findById(Integer.valueOf(id));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) throws Throwable {
        return this.service.delete(Integer.valueOf(id));
    }

    @PutMapping
    public Producto update(@RequestBody Producto producto)  throws Throwable {
        return this.service.save(producto);
    }

    @GetMapping("/masVendido")
    public ResponseEntity<Producto> getMasVendido() {
        Producto p = this.service.getMasVendido();
        if(p == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

}
