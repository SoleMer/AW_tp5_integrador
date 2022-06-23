package com.aw.awtp5.controllers;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comprar")
public class VentaController {

    @Autowired
    VentaService service;

    @PostMapping
    public boolean save(@RequestBody DetalleVentaDTO detalleVentaDTO)  throws Throwable {
        return this.service.save(detalleVentaDTO);
    }
}
