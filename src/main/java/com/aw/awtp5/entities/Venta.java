package com.aw.awtp5.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'venta'
 * @author arana-marsico-merino
 * @version 1.0
 */
@Entity
@Table(name = "venta", schema = "tp5_integrador", catalog = "")
public class Venta {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "cliente_id")
    private int clienteId;
    @Basic
    @Column(name = "fecha")
    private LocalDate fecha;

    public Venta() {
    }

    public Venta(int id, int clienteId, LocalDate fecha) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
    }

    public Venta(int clienteId, LocalDate fecha) {
        this.clienteId = clienteId;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta that = (Venta) o;
        return id == that.id && clienteId == that.clienteId && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clienteId, fecha);
    }
}
