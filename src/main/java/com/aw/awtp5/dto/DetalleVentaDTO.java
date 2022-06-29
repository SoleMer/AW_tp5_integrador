package com.aw.awtp5.dto;

import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Venta;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class DetalleVentaDTO {

    private int clienteId;

    private List<DetalleVenta> detalles;

	public DetalleVentaDTO(int clienteId, List<DetalleVenta> detalles){
		this.detalles = new ArrayList<>();
		this.detalles = detalles;
		this.clienteId = clienteId;
	}

	public List<DetalleVenta> getProductos() {
		return detalles;
	}

	public void setDetalles(List<DetalleVenta> detalles) {
		this.detalles = detalles;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int id){
		this.clienteId = id;
	}

	@Override
	public String toString() {
		return "DetalleVentaDTO{" +
				"clienteId=" + clienteId +
				", detalles=" + detalles +
				'}';
	}
}
