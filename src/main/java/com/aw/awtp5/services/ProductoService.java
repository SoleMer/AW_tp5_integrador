package com.aw.awtp5.services;

import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.ropositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Servicio encargado de la lógica de negocio relacionada con los productos
 * <p>Es accedido por: ProductoController</p>
 * @author arana-marsico-merino
 * @version 1.0
 */
@Service("ProductoService")
public class ProductoService {

    @Autowired
    ProductoRepository repository;

    /**
     * Realiza la petición de todos los productos al repositorio
     * @return Lista de toos los Productos
     * @throws Throwable
     */
    @Transactional
    public List<Producto> getAll() throws Throwable {
        return this.repository.findAll();
    }

    /**
     * Realiza el llamado al repositorio para guardar el producto en la base de datos
     * <p>También se utiliza para guardar cambios en un producto anteriormente almacenado en la base de datos</p>
     * @param producto a guardar
     * @return boolean indicador del resultado de la transacción
     * @throws Throwable
     */
    @Transactional
    public boolean save(Producto producto)  throws Throwable {
        Producto p = this.repository.save(producto);
        return p != null;
    }

    /**
     * Realiza el llamado al repositorio para eliminar un producto según su id
     * <p>Verifica qe el producto exista</p>
     * @param id del producto a eliminar
     * @return boolean indicador de resultado de la transacción
     * @throws Throwable
     */
    @Transactional
    public boolean delete(int id) throws Throwable {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Realiza la petición del producto solicitado al repositorio
     * @param id del producto buscado
     * @return Producto resultado de la búsqueda
     */
    @Transactional
    public Producto findById(Integer id) {
        return this.repository.findProductoById(id);
    }

    /**
     * Realiza la petición del producto más vendido al repositorio
     * @return Producto resultado de la búsqueda
     */
    @Transactional
    public Producto getMasVendido() {
        List<Producto> productos = this.repository.getMasVendido();
        if(productos.isEmpty()){
            return null;
        }
        return this.repository.getMasVendido().get(0);
    }
}
