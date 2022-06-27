package com.aw.awtp5.controllers;

import com.aw.awtp5.entities.Cliente;
import com.aw.awtp5.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public Cliente update(@RequestBody Cliente cliente) throws Throwable {
        return this.service.save(cliente);
    }

    /* deberiamos usar el id?*/
    @DeleteMapping
    public void delete(@RequestBody Cliente cliente) throws Throwable {
        this.service.delete(cliente);
    }

}
