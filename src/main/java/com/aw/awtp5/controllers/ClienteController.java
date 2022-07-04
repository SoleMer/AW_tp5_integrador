package com.aw.awtp5.controllers;

import com.aw.awtp5.dto.ClienteGastoDTO;
import com.aw.awtp5.entities.Cliente;
import com.aw.awtp5.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador accesible con el path "/cliente"
 * <p>Se encarga de todas las peticiones relacionadas con la entidad 'Cliente' y la respectiva tabla en la base de datos</p>
 * @author arana-marsico-merino
 * @version 1.0
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    /**
     * Realiza la petición de guardado de un cliente al servicio.
     * <p>Se accede mediante el método post</p>
     * @param cliente a guardar
     * @return resultado de la transacción
     * @throws Throwable
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Cliente cliente) throws Throwable {
        if (this.service.save(cliente))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Solicita el listado de clientes al servicio.
     * <p>Se accede mediante el método get</p>
     * @return Lista de Clientes
     * @throws Throwable
     */
    @GetMapping
    public List<Cliente> getAll() throws Throwable {
        return this.service.getAll();
    }

    /**
     * Solicita al servicio un cliente por su id
     * <p>Se accede mediante el método get y al path inicial se le agrega el id solicitado</p>
     * @param id representa el identificador único del cliente al que se quiere acceder
     * @return Cliente
     * @throws Throwable
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable String id) throws Throwable {
        Cliente c = this.service.findById(Integer.valueOf(id));
        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    /**
     * Solicita al servicio la modificación de los datos de un cliente
     * <p>Se accede mediante el método put</p>
     * @param cliente con datos nuevos
     * @return resultado de la transacción
     * @throws Throwable
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Cliente cliente) throws Throwable {
        return this.save(cliente);
    }

    /**
     * Solicita al servicio la eliminación de un Cliente según su id
     * <p>Se accede mediante el método delete y al path inicial se le agrega el id del cliente a eliminar</p>
     * @param id del cliente a eliminar
     * @return resultado de la transacción
     * @throws Throwable posible excepción
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id)  throws Throwable{
        if (this.service.delete(Integer.valueOf(id)))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Solicita al servicio el total de gastos realizados por cada cliente
     * <p>Se accede mediante el método get y al path nicial se le agrega "/gastos"</p>
     * @return Lista de ClienteGastoDTO
     * @throws Throwable posible excepción
     */
    @GetMapping("/gastos")
    public List<ClienteGastoDTO> getTotalCompras() throws Throwable{
        return this.service.getTotalCompras();
    }

}
