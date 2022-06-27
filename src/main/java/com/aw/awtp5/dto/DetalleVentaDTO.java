package com.aw.awtp5.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class DetalleVentaDTO {

    private int clienteId;

    private Map<Integer, Integer> productos;

	public Map<Integer, Integer> getProductos() {
		return productos;
	}

	public int getClienteId() {
		return clienteId;
	}

}
