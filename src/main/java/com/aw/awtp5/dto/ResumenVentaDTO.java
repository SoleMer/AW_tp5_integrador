package com.aw.awtp5.dto;

import lombok.Getter;

import java.time.LocalDate;

/**
 * DTO que resume la cantidad de productos que fueron vendidos a un cliente en una fecha
 * @author arana-marsico-merino
 * @version 1.0
 */
@Getter
public class ResumenVentaDTO {

    private int clienteId;
    private LocalDate fecha;
    private Long cantidadProductos;

    public ResumenVentaDTO(int clienteId, LocalDate fecha, Long cantidadProductos) {
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.cantidadProductos = cantidadProductos;
    }
}
