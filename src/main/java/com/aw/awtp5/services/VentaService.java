package com.aw.awtp5.services;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Venta;
import com.aw.awtp5.ropositories.DetalleVentaRepository;
import com.aw.awtp5.ropositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("VentaService")
public class VentaService {

    @Autowired
    VentaRepository repository;

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaDTO save(DetalleVentaDTO detalleVentaDTO) {
        try {

            int suma = this.getProductosDiarios(detalleVentaDTO.getClienteId());//0; // inicializar a partir de los productos que compro en el dia;
            if(suma >= 3) {
                return null;
            }

            for (DetalleVenta dv: detalleVentaDTO.getProductos()) {
                suma += dv.getCantidad();
            }
            System.out.println(suma);
            if (suma <= 3) {

                Venta venta = new Venta(detalleVentaDTO.getClienteId(), LocalDate.now());
                Venta v = this.repository.save(venta);
                for (DetalleVenta detalleVenta : detalleVentaDTO.getProductos()) {
                    detalleVenta.setVentaId(v.getId());
                    this.detalleVentaRepository.save(detalleVenta);
                }
                return detalleVentaDTO;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private int getProductosDiarios(int clienteId) {
        return this.repository.getCantidadProductosDeHoy(clienteId);
    }

}
