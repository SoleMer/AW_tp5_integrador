package com.aw.awtp5.services;

import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.ropositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean delete(int id) throws Throwable {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Producto> findById(Integer id) {
        return this.repository.findById(id);
    }
}
