package com.aw.awtp5.dto;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class VentasDiariasDTO {

    private LocalDate fecha;

    private String producto;

    private double total;

    private long cantidad;

    public VentasDiariasDTO() {
        super();
    }

    public VentasDiariasDTO(LocalDate fecha, String producto, double total, long cantidad) {
        super();
        this.fecha = fecha;
        this.producto = producto;
        this.total = total;
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
