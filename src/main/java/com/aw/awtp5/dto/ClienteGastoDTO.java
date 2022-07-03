package com.aw.awtp5.dto;

import lombok.Getter;

/**
 * DTO que resume el nombre completo de un cliente y el total de sus compras
 * @author arana-marsico-merino
 * @version 1.0
 */
@Getter
public class ClienteGastoDTO {
    private String nombreApellido;
    private double totalCompras;

    public ClienteGastoDTO(String nombreApellido, double totalCompras) {
        this.nombreApellido = nombreApellido;
        System.out.println(totalCompras);
        this.totalCompras = totalCompras;
    }

}
