package com.aw.awtp5.services;

import com.aw.awtp5.controllers.ProductoController;
import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.entities.Venta;
import com.aw.awtp5.ropositories.DetalleVentaRepository;
import com.aw.awtp5.ropositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("VentaService")
public class VentaService {

    @Autowired
    VentaRepository repository;

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @Autowired
    ProductoController productoController;

    @Autowired
    ProductoService productoService;

    @Transactional
    public DetalleVentaDTO save(DetalleVentaDTO detalleVentaDTO) throws Throwable {
        int comprasDeHoy = this.getProductosDiarios(detalleVentaDTO.getClienteId());
        if (comprasDeHoy >= 3) {
            return null;
        }

        int totalCompras = this.sumarComprasDelCarrito(comprasDeHoy, detalleVentaDTO.getProductos());

        if (totalCompras > 3) {
            return null;
        }

        if (!this.verificarProductos(detalleVentaDTO.getProductos())) {
            return null;
        }

        Venta venta = new Venta(detalleVentaDTO.getClienteId(), LocalDate.now());
        Venta v = this.repository.save(venta);


        for (DetalleVenta detalleVenta : detalleVentaDTO.getProductos()) {

            detalleVenta.setVentaId(v.getId());

            this.detalleVentaRepository.save(detalleVenta);

            Producto p = this.productoService.findById(detalleVenta.getProductoId());
            p.setStock(p.getStock() - detalleVenta.getCantidad());

            this.productoService.save(p);

        }


        return detalleVentaDTO;

    }


    private boolean verificarProductos(List<DetalleVenta> productos) {
        ArrayList<Producto> lista = new ArrayList<>();
        for (DetalleVenta dv : productos) {

            Producto p = this.productoService.findById(dv.getProductoId());
            if (lista.contains(p)) {
                return false;
            }
            lista.add(p);
            if (p.getStock() < dv.getCantidad()) {
                return false;
            }
        }
        return true;
    }


    private int sumarComprasDelCarrito(int totalCompras, List<DetalleVenta> productos) {
        for (DetalleVenta dv : productos) {
            totalCompras += dv.getCantidad();
        }
        return totalCompras;
    }

    @Transactional
    public int getProductosDiarios(int clienteId) {
        return this.repository.getCantidadProductosDeHoy(clienteId);
    }

    @Transactional
    public List<Venta> getVentasPorDia() {
        return this.repository.getAll();
    }

    @Transactional
    public boolean delete(int id) throws Throwable{
        if(this.repository.existsById(id)) {
            this.detalleVentaRepository.deleteAllByVentaId(id);
            this.repository.deleteVentaById(id);
            return true;
        }
        return false;
    }

    /*
    public boolean update(Venta venta) {
        DetalleVentaDTO dv = this.detalleVentaRepository.getById(venta.getId()); //TODO
        if (v.getClienteId() != venta.getClienteId()){

        } else if (!dv.getFecha().equals(venta.getFecha())) {
            //obtengo detalleVentaDto (cliente, fecha)
            //compruebo si la cantidad de fechaNueva + dv.cantidad <= 3
                //edito
            //return false;
        }
        return false;
    }
    */
}
