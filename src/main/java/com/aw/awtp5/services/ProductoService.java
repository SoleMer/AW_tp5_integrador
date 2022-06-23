package com.aw.awtp5.services;

import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.ropositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductoService")
public class ProductoService {

    @Autowired
    ProductoRepository repository;

    public List<Producto> getAll() throws Throwable {
        return this.repository.findAll();
    }

    public Producto save(Producto producto)  throws Throwable {
        return this.repository.save(producto);
    }

    public void delete(Producto p) throws Throwable {
        this.repository.delete(p);
    }
}
