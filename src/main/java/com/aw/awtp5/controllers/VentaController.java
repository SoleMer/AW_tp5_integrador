package com.aw.awtp5.controllers;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.dto.VentasDiariasDTO;
import com.aw.awtp5.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    VentaService service;

    @PostMapping
    public boolean save(@RequestBody DetalleVentaDTO detalleVentaDTO)  throws Throwable {
        return this.service.save(detalleVentaDTO);
    }

    @GetMapping("/ventasDiarias")
    public List<VentasDiariasDTO> getVentasDiarias(){
        return this.service.getVentasDiarias();
    }
}
