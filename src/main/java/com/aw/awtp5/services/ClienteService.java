package com.aw.awtp5.services;

import com.aw.awtp5.dto.ClienteGastoDTO;
import com.aw.awtp5.entities.Cliente;
import com.aw.awtp5.ropositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Servicio encargado de la lógica de negocio relacionada con los clientes
 * <p>Es accedido por: ClienteController</p>
 * @author arana-marsico-merino
 * @version 1.0
 */
@Service("ClienteService")
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    /**
     * Realiza la petición de todos los clientes al repositorio
     * @return Lista de toos los Clientes
     * @throws Throwable
     */
    @Transactional
    public List<Cliente> getAll() throws Throwable {
        return this.repository.findAll();
    }

    /**
     * Realiza el llamado al repositorio para guardar el cliente en la base de datos
     * <p>También se utiliza para guardar cambios en un cliente anteriormente almacenado en la base de datos</p>
     * @param cliente a guardar
     * @return boolean indicador del resultado de la transacción
     * @throws Throwable
     */
    @Transactional
    public boolean save(Cliente cliente) throws Throwable {
        Cliente c = this.repository.save(cliente);
        return c != null;
    }

    /**
     * Realiza el llamado al repositorio para eliminar un cliente según su id
     * <p>Verifica qe el cliente exista</p>
     * @param id del cliente a eliminar
     * @return boolean indicador de resultado de la transacción
     * @throws Throwable
     */
    @Transactional
    public boolean delete(int id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Realiza la petición del cliente solicitado al repositorio
     * @param id del cliente buscado
     * @return Cliente resultado de la búsqueda
     */
    @Transactional
    public Cliente findById(int id) {
        return this.repository.findClienteById(id);
    }

    /**
     * Solicita al repositorio el total de gastos realizados por cada cliente
     * @return
     * @throws Throwable
     */
    @Transactional
    public List<ClienteGastoDTO> getTotalCompras() throws Throwable {
        return this.repository.getTotalCompras();
    }
}
