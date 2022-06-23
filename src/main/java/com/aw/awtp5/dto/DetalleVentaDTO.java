package com.aw.awtp5.dto;

import com.aw.awtp5.entities.DetalleVentaPK;
import lombok.Getter;

import javax.persistence.*;
import java.util.Map;

@Getter
public class DetalleVentaDTO {

    private int clienteId;

    private Map<Integer, Integer> productos;

}
