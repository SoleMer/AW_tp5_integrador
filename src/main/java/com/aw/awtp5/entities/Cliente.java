package com.aw.awtp5.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cliente", schema = "tp5_integrador", catalog = "")
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre_apellido")
    private String nombreApellido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente that = (Cliente) o;
        return id == that.id && Objects.equals(nombreApellido, that.nombreApellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreApellido);
    }
}
