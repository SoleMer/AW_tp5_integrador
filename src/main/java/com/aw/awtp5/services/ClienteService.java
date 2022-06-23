package com.aw.awtp5.services;

import com.aw.awtp5.entities.Cliente;
import com.aw.awtp5.ropositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClienteService")
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public List<Cliente> getAll() throws Throwable {
        return this.repository.findAll();
    }

    public Cliente save(Cliente cliente) throws Throwable {
        return  this.repository.save(cliente);
    }

    public void delete(Cliente cliente) throws Throwable {
        this.repository.delete(cliente);
    }
}
