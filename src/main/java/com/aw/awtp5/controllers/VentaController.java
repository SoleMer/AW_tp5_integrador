package com.aw.awtp5.controllers;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.dto.MontoTotalClientesDTO;
import com.aw.awtp5.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    VentaService service;

    @PostMapping
    public DetalleVentaDTO save(@RequestBody DetalleVentaDTO detalleVentaDTO)  throws Throwable {
        return this.service.save(detalleVentaDTO);
    }

    @GetMapping("/ventasClientes")
    public MontoTotalClientesDTO getVentasCliente(){
        return this.service.getRepoteClientesMonto();
    }
}
