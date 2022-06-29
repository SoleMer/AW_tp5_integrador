package com.aw.awtp5.dto;

import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Venta;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class DetalleVentaDTO {

    private Venta venta;

    private List<DetalleVenta> detalles;

	public DetalleVentaDTO(){
		detalles = new ArrayList<>();
	}

	public List<DetalleVenta> getProductos() {
		return detalles;
	}

	public int getClienteId() {
		return venta.getClienteId();
	}

}
