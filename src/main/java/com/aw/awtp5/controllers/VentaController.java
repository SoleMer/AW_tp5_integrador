package com.aw.awtp5.controllers;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.dto.VentasDiariasDTO;
import com.aw.awtp5.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    VentaService service;

    @PostMapping
    public ResponseEntity<DetalleVentaDTO> save(@RequestBody DetalleVentaDTO detalleVentaDTO)  throws Throwable {
        DetalleVentaDTO respuesta = this.service.save(detalleVentaDTO);
        if (respuesta == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}
