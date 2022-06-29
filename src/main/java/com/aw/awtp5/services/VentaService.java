package com.aw.awtp5.services;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.dto.VentasDiariasDTO;
import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Venta;
import com.aw.awtp5.ropositories.DetalleVentaRepository;
import com.aw.awtp5.ropositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("VentaService")
public class VentaService {

    @Autowired
    VentaRepository repository;

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    public boolean save(DetalleVentaDTO detalleVentaDTO) {
        try {
            int suma = 0;
            for (Integer cantidad: detalleVentaDTO.getProductos().values()) {
                suma += cantidad;
            }
            if (suma < 4) {
                Venta venta = new Venta(detalleVentaDTO.getClienteId(), LocalDate.now());
                Venta v = this.repository.save(venta);
                for (Map.Entry<Integer, Integer> p : detalleVentaDTO.getProductos().entrySet()) {
                    DetalleVenta detalleVenta = new DetalleVenta(p.getKey(), v.getId(), p.getValue());
                    this.detalleVentaRepository.save(detalleVenta);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<VentasDiariasDTO> getVentasDiarias() {
        return this.detalleVentaRepository.getVentasDiarias();
    }
}
