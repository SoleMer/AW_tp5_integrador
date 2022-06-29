package com.aw.awtp5.dto;

import com.aw.awtp5.entities.Cliente;
import lombok.Getter;

@Getter
public class MontoTotalClientesDTO {

    private Cliente c;

    private float monto;

    public MontoTotalClientesDTO(Cliente c, float monto) {
        this.c = c;
        this.monto = monto;
    }

    public Cliente getCliente() {
        return c;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
}
