package com.aw.awtp5.ropositories;

import com.aw.awtp5.dto.ResumenVentaDTO;
import com.aw.awtp5.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio encargado de las transacciones con la BBDD correspondientes a la tabla venta
 * @author arana-marsico-merino
 * @version 1.0
 */
@Repository("VentaRepository")
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    /**
     * Obtiene la cantidad de productos vendidos a un cliente particular en una fecha indicada
     * @param fecha buscada
     * @param clienteId buscado
     * @return cantidad de productos vendidos en el día a un cliente
     */
    @Query("SELECT COALESCE(sum(dv.cantidad), 0) " +
            "FROM Venta v " +
            "JOIN DetalleVenta dv ON v.id = dv.ventaId AND v.fecha = ?1 " +
            "WHERE v.clienteId = ?2 ")
    int getCantidadProductosDeHoy(LocalDate fecha, int clienteId);

    /**
     * Obtiene el listado de Ventas ordenadas por fecha
     * @return Lista de ventas
     */
    @Query("select v from Venta v order by v.fecha desc")
    List<Venta> getAll();

    /**
     * Elimina una venta por id
     * @param id de la venta que se desea eliminar
     */
    public void deleteVentaById(int id);

    /**
     * Obtiene un resumen de venta que incluye la fecha, el cliente y la cantidad total de productos de la venta
     * @param id de la venta de la cual se solicita eol detalle
     * @return resumen de venta
     */
    @Query("select new com.aw.awtp5.dto.ResumenVentaDTO(v.clienteId, v. fecha, sum(dv.cantidad)) " +
            "from Venta v join DetalleVenta dv on v.id = dv.ventaId where v.id = ?1")
    public ResumenVentaDTO getById(int id);

    /**
     * Obtiene una lista de ventas ordenadas descendientemente por id
     * @return Lista de ventas
     */
    @Query("select new Venta(v.id, v.clienteId, v.fecha) from Venta v order by v.id desc")
    public List<Venta> getUltimaInsercion();
}
