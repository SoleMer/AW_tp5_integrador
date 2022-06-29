package com.aw.awtp5.dto;

public class ClienteGastoDTO {
    private String nombreApellido;
    private double totalCompras;

    public ClienteGastoDTO(String nombreApellido, double totalCompras) {
        this.nombreApellido = nombreApellido;
        System.out.println(totalCompras);
        this.totalCompras = totalCompras;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public double getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(double totalCompras) {
        this.totalCompras = totalCompras;
    }
}
