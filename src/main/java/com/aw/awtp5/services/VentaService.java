package com.aw.awtp5.services;

import com.aw.awtp5.controllers.ProductoController;
import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.dto.ResumenVentaDTO;
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

/**
 * Servicio encargado de la lógica de negocio relacionada con lass ventas
 * <p>Es accedido por: VentaController</p>
 * @author arana-marsico-merino
 * @version 1.0
 */
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

    /**
     * Realiza el llamado al repositorio para guardar la venta en la base de datos
     * <p>Comprueba que la cantidad de productos comprados ese día por el cliente,
     * sumado a la cantidad de prodctos que intenta comprar, no supere la cantidad prefijada.
     * Comprueba que los productos tengan stock suficiente para la compra.
     * Guarda la venta y, posteriormente, el detalle de la venta para cada producto</p>
     * @param detalleVentaDTO venta y su detalle a guardar
     * @return boolean indicador del resultado de la transacción
     * @throws Throwable
     */
    @Transactional
    public boolean save(DetalleVentaDTO detalleVentaDTO) throws Throwable {
        int comprasDeHoy = this.getProductosDiarios(LocalDate.now(), detalleVentaDTO.getClienteId());
        if (comprasDeHoy >= 3) {
            return false;
        }

        int totalCompras = this.sumarComprasDelCarrito(comprasDeHoy, detalleVentaDTO.getProductos());

        if (totalCompras > 3) {
            return false;
        }

        if (!this.verificarProductos(detalleVentaDTO.getProductos())) {
            return false;
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
        return true;
    }

    /**
     * Verifica que el stock de cada producto sea suficiente para la venta que se desea registrar
     * y que no haya dos productos iguales en la misma venta
     * @param productos
     * @return
     */
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

    /**
     * Suma la cantidad de productos que se desean comprar más la cantidad de productos comprados por el cliente en el día
     * @param totalCompras
     * @param productos
     * @return total de productos resultante en el caso de realizar la venta
     */
    private int sumarComprasDelCarrito(int totalCompras, List<DetalleVenta> productos) {
        for (DetalleVenta dv : productos) {
            totalCompras += dv.getCantidad();
        }
        return totalCompras;
    }

    /**
     * Obtiene la cantidad de productos vendidos en el día al cliente que desea realizar la compra
     * @param fecha
     * @param clienteId
     * @return cantidad de productos vendidos al cliente
     */
    @Transactional
    public int getProductosDiarios(LocalDate fecha, int clienteId) {
        return this.repository.getCantidadProductosDeHoy(fecha, clienteId);
    }

    /**
     * Realiza la petición de todas las ventas por día al repositorio
     * @return Lista de todas las Ventas
     * @throws Throwable
     */
    @Transactional
    public List<Venta> getVentasPorDia() {
        return this.repository.getAll();
    }

    /**
     * Realiza el llamado al repositorio para eliminar una venta según su id
     * <p>Verifica que la venta exista.
     * Elimina todos los detalles de la venta que se desea eliminar.
     * Elimina la venta.</p>
     * @param id de la venta a eliminar
     * @return boolean indicador de resultado de la transacción
     * @throws Throwable
     */
    @Transactional
    public boolean delete(int id) throws Throwable{
        if(this.repository.existsById(id)) {
            this.detalleVentaRepository.deleteAllByVentaId(id);
            this.repository.deleteVentaById(id);
            return true;
        }
        return false;
    }

    /**
     * Verifica si se quieren editar el cliente la fecha o ambos datos.
     * <p>Realiza el llamado al método encargado de verificasr si se puede guardar la venta</p>
     * @param nuevaVenta
     * @return
     */
    public boolean update(Venta nuevaVenta) {
        ResumenVentaDTO v = this.repository.getById(nuevaVenta.getId());

        if((v.getClienteId() != nuevaVenta.getClienteId()) && !(v.getFecha().equals(nuevaVenta.getFecha()))) {
            return this.edit(nuevaVenta.getFecha(), nuevaVenta.getClienteId(), v.getCantidadProductos(), nuevaVenta);
        } else {
            if (v.getClienteId() != nuevaVenta.getClienteId()){
                return this.edit(v.getFecha(), nuevaVenta.getClienteId(), v.getCantidadProductos(), nuevaVenta);
            } else if (!v.getFecha().equals(nuevaVenta.getFecha())) {
                return this.edit(nuevaVenta.getFecha(), v.getClienteId(), v.getCantidadProductos(), nuevaVenta);
            }
            return false;
        }
    }

    /**
     * Solicita al repositorio la ctualización de la información de la compra
     * <p>Verifica que el cliente no supere la restricción de cantidad de productos en la fecha de la compra.
     * Realiza el llamado al repositorio para completar la transacción</p>
     * @param fecha
     * @param clienteId
     * @param cantidadActual
     * @param nuevaVenta
     * @return
     */
    public boolean edit(LocalDate fecha, int clienteId, Long cantidadActual, Venta nuevaVenta) {
        int cantidad = this.getProductosDiarios(fecha, clienteId);
        if (cantidadActual + cantidad <= 3) {
            this.repository.save(nuevaVenta);
            return true;
        }
        return false;
    }

}
