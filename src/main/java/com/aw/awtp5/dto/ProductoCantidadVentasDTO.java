package com.aw.awtp5.dto;

import lombok.Getter;

@Getter
public class ProductoCantidadVentasDTO {

    private String nombre;
    private int stock;
    private double precio;
    private int cantidadVendidos;

    public ProductoCantidadVentasDTO(String nombre, int stock, double precio, int cantidadVendidos) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.cantidadVendidos = cantidadVendidos;
    }
}
