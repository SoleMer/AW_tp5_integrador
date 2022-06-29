package com.aw.awtp5.controllers;

import com.aw.awtp5.entities.Cliente;
import com.aw.awtp5.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) throws Throwable {
        return this.service.save(cliente);
    }

    @GetMapping
    public List<Cliente> getAll() throws Throwable {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> findById(@PathVariable String id) throws Throwable {
        return this.service.findById(Integer.valueOf(id));
    }

    @PutMapping
    public Cliente update(@RequestBody Cliente cliente) throws Throwable {
        return this.service.save(cliente);
    }
    
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return this.service.delete(Integer.valueOf(id));
    }

}
