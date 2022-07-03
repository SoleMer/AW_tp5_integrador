package com.aw.awtp5.controllers;

import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador accesible con el path "/producto"
 * <p>Se encarga de todas las peticiones relacionadas con la entidad 'Producto' y la respectiva tabla en la base de datos</p>
 * @author arana-marsico-merino
 * @version 1.0
 */
@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService service;

    /**
     * Realiza la petición de guardado de un producto al servicio.
     * <p>Se accede mediante el método post</p>
     * @param producto el producto a guardar
     * @return resultado de la transacción
     * @throws Throwable
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Producto producto)  throws Throwable {
        if (this.service.save(producto))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Solicita el listado de productos al servicio.
     * <p>Se accede mediante el método get</p>
     * @return Lista de Productos
     * @throws Throwable
     */
    @GetMapping
    public List<Producto> getAll() throws Throwable {
        return this.service.getAll();
    }

    /**
     * Solicita al servicio un producto poo su id
     * <p>Se accede mediante el método get y al path inicial se le agrega el id solicitado</p>
     * @param id representa el identificador único del poducto al que se quiere acceder
     * @return Producto
     * @throws Throwable
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable String id) throws Throwable {
        Producto p = this.service.findById(Integer.valueOf(id));
        if (p == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    /**
     * Solicita al servicio la eliminación de un Producto según su id
     * <p>Se accede mediante el método delete y al path inicial se le agrega el id del producto a eliminar</p>
     * @param id del producto a eliminar
     * @return resultado de la transacción
     * @throws Throwable
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws Throwable {
        if (this.service.delete(Integer.valueOf(id)))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Solicita al servicio la modificación de los datos de un producto
     * <p>Se accede mediante el método put</p>
     * @param producto con datos nuevos
     * @return resultado de la transacción
     * @throws Throwable
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Producto producto)  throws Throwable {
        return this.save(producto);
    }

    /**
     * Solicita al servicio el producto más vendido.
     * <p>Se accede mediante el método get y al path inicial se le agrega "/masVendido"</p>
     * @return resultado de la transacción
     */
    @GetMapping("/masVendido")
    public ResponseEntity<Producto> getMasVendido() {
        Producto p = this.service.getMasVendido();
        if(p == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

}
