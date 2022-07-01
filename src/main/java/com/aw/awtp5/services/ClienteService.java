package com.aw.awtp5.services;

import com.aw.awtp5.dto.ClienteGastoDTO;
import com.aw.awtp5.entities.Cliente;
import com.aw.awtp5.ropositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("ClienteService")
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    @Transactional
    public List<Cliente> getAll() throws Throwable {
        return this.repository.findAll();
    }

    @Transactional
    public Cliente save(Cliente cliente) throws Throwable {
        return  this.repository.save(cliente);
    }

    @Transactional
    public boolean delete(int id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Cliente> findById(int id) {
        return this.repository.findById(id);
    }

    @Transactional
    public List<ClienteGastoDTO> getTotalCompras() throws Throwable {
        return this.repository.getTotalCompras();
    }
}
