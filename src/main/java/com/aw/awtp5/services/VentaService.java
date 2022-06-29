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
            int suma = 0;
            for (DetalleVenta dv: detalleVentaDTO.getProductos()) {
                suma += dv.getCantidad();
            }
            if (suma < 4) {
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

}
