package com.aw.awtp5.dto;

public class ClienteGastoDTO {
    private String nombreApellido;
    private double totalCompras;

    public ClienteGastoDTO(String nombreApellido, double totalCompras) {
        this.nombreApellido = nombreApellido;
        this.totalCompras = totalCompras;
    }
}
