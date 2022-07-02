package com.aw.awtp5.controllers;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.entities.Venta;
import com.aw.awtp5.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador accesible con el path "/venta"
 * <p>Se encarga de todas las peticiones relacionadas con la entidad 'Venta' y la respectiva tabla en la base de datos</p>
 * @author arana-marsico-merino
 * @version 1.0
 */
@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    VentaService service;

    /**
     * Realiza la petición de guardado de una venta al servicio.
     * <p>Se accede mediante el método post</p>
     * @param detalleVentaDTO detalles de la venta y su detalle a guardar
     * @return resultado de la transacción
     * @throws Throwable
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody DetalleVentaDTO detalleVentaDTO)  throws Throwable {
        if (this.service.save(detalleVentaDTO))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Solicita el listado de ventas ordenadas por día al servicio
     * <p>Se accede mediante el método get</p>
     * @return Lista de Ventas
     * @throws Throwable
     */
    @GetMapping
    public List<Venta> getVentasPorDia() {
        return this.service.getVentasPorDia();
    }

    /**
     * Solicita al servicio la eliminación de una Venta según su id
     * <p>Se accede mediante el método delete y al path inicial se le agrega el id de la venta a eliminar</p>
     * @param id de la venta a eliminar
     * @return resultado de la transacción
     * @throws Throwable
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) throws Throwable{
        if (this.service.delete(Integer.valueOf(id)))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Solicita al servicio la modificación de los datos de una venta
     * <p>Se accede mediante el método put</p>
     * @param venta con datos nuevos
     * @return resultado de la transacción
     * @throws Throwable
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Venta venta) {
        if (this.service.update(venta))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
