package com.aw.awtp5.services;

import com.aw.awtp5.controllers.ProductoController;
import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.dto.VentasDiariasDTO;
import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Venta;
import com.aw.awtp5.ropositories.DetalleVentaRepository;
import com.aw.awtp5.ropositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.Iterator;
import java.util.List;

@Service("VentaService")
public class VentaService {

    @Autowired
    VentaRepository repository;

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @Autowired
    ProductoController productoController;

    public DetalleVentaDTO save(DetalleVentaDTO detalleVentaDTO) {
        try {

            int comprasDeHoy = this.getProductosDiarios(detalleVentaDTO.getClienteId());//0; // inicializar a partir de los productos que compro en el dia;
            if(comprasDeHoy >= 3) {
                return null;
            }

            int totalCompras = this.sumarComprasDelCarrito(comprasDeHoy, detalleVentaDTO.getProductos());
            if (totalCompras > 3) {
                return null;
            }
            System.out.println(totalCompras);

            for(DetalleVenta dv : detalleVentaDTO.getProductos()) {
                this.productoController.

            }


            Venta venta = new Venta(detalleVentaDTO.getClienteId(), LocalDate.now());
            Venta v = this.repository.save(venta);
            for (DetalleVenta detalleVenta : detalleVentaDTO.getProductos()) {
                detalleVenta.setVentaId(v.getId());
                this.detalleVentaRepository.save(detalleVenta);
            }
            return detalleVentaDTO;

        } catch (Exception e) {
            return null;
        }
    }

    private int sumarComprasDelCarrito(int totalCompras, List<DetalleVenta> productos) {
        for (DetalleVenta dv: productos) {
            totalCompras += dv.getCantidad();
        }
        return totalCompras;
    }

    private int getProductosDiarios(int clienteId) {
        return this.repository.getCantidadProductosDeHoy(clienteId);
    }

}
