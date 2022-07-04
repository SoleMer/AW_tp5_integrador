package com.aw.awtp5.dto;

import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Venta;
import lombok.Getter;

import java.util.ArrayList;

/**
 * DTO que resume la la venta y todos los productos contenidos en la misma
 * @author arana-marsico-merino
 * @version 1.0
 */
@Getter
public class VentaDetalleDTO {

    private Venta venta;
    private ArrayList<DetalleVenta> detallesVentas;

    /**
     * Recibe una venta e inicializa la lita
     * @param venta
     */
    public VentaDetalleDTO(Venta venta) {
        this.venta = venta;
        this.detallesVentas = new ArrayList<>();
    }

    /**
     * Agrega un detalle de Venta a la lista de detalles de venta
     * @param dv detalle de venta
     */
    public void addDetalle(DetalleVenta dv ) {
        this.detallesVentas.add(dv);
    }
}
