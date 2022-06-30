package com.aw.awtp5.controllers;

import com.aw.awtp5.dto.ProductoCantidadVentasDTO;
import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Producto> findById(@PathVariable String id) throws Throwable {
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
    public List<ProductoCantidadVentasDTO> getMasVendido() {
        return this.service.getMasVendido();
    }

}
