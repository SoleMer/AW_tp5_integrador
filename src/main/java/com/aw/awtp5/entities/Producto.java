package com.aw.awtp5.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'producto'
 * @author arana-marsico-merino
 * @version 1.0
 */
@Entity
@Table(name = "producto", schema = "tp5_integrador", catalog = "")
public class Producto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "stock")
    private int stock;
    @Basic
    @Column(name = "precio")
    private double precio;

    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public Producto(int id, String nombre, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public Producto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto that = (Producto) o;
        return id == that.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, stock, precio);
    }
}
