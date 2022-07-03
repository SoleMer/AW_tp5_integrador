package com.aw.awtp5.dto;

import com.aw.awtp5.entities.DetalleVenta;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO qe resume el id de un cliente y el listado de productos y la cantidad que se desea vender
 * Se utiliza para recibir información por parte del usuario, que luego será almacenada en las tablas venta y detalle_venta
 * @author arana-marsico-merino
 * @version 1.0
 */
@Getter
public class DetalleVentaDTO {

	private int clienteId;

	private List<DetalleVenta> productos;

	public DetalleVentaDTO(int clienteId, List<DetalleVenta> productos){
		this.productos = new ArrayList<>();
		this.productos = productos;
		this.clienteId = clienteId;
	}

	public List<DetalleVenta> getProductos() {
		return productos;
	}

	public void setDetalles(List<DetalleVenta> productos) {
		this.productos = productos;
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
				", detalles=" + productos +
				'}';
	}
}
