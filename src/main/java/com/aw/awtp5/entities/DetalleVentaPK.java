package com.aw.awtp5.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DetalleVentaPK implements Serializable {
    @Column(name = "Producto_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productoId;
    @Column(name = "Venta_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ventaId;

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleVentaPK that = (DetalleVentaPK) o;
        return productoId == that.productoId && ventaId == that.ventaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, ventaId);
    }
}
