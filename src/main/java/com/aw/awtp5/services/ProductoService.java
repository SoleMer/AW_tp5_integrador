package com.aw.awtp5.services;

import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.ropositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("ProductoService")
public class ProductoService {

    @Autowired
    ProductoRepository repository;

    @Transactional
    public List<Producto> getAll() throws Throwable {
        return this.repository.findAll();
    }

    @Transactional
    public Producto save(Producto producto)  throws Throwable {
        return this.repository.save(producto);
    }

    @Transactional
    public boolean delete(int id) throws Throwable {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Producto findById(Integer id) {
        return this.repository.findProductoById(id);
    }

    @Transactional
    public Producto getMasVendido() {
        List<Producto> productos = this.repository.getMasVendido();
        if(productos.isEmpty()){
            return null;
        }
        return this.repository.getMasVendido().get(0);
    }
}
