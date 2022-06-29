package com.aw.awtp5.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "detalle_venta", schema = "tp5_integrador", catalog = "")
@IdClass(DetalleVentaPK.class)
public class DetalleVenta {

    @Id
    @Column(name = "Producto_id")
    private int productoId;

    @Id
    @Column(name = "Venta_id")
    private int ventaId;

    @Basic
    @Column(name = "cantidad")
    private int cantidad;

    public DetalleVenta(int productoId, int ventaId, int cantidad) {
        this.productoId = productoId;
        this.ventaId = ventaId;
        this.cantidad = cantidad;
    }

    public DetalleVenta(int productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public DetalleVenta() {
    }

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleVenta that = (DetalleVenta) o;
        return productoId == that.productoId && ventaId == that.ventaId && cantidad == that.cantidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, ventaId, cantidad);
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "productoId=" + productoId +
                ", ventaId=" + ventaId +
                ", cantidad=" + cantidad +
                '}';
    }
}
