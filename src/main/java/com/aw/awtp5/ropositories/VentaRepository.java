package com.aw.awtp5.ropositories;

import com.aw.awtp5.dto.ResumenVentaDTO;
import com.aw.awtp5.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("VentaRepository")
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query(value = "SELECT COALESCE(sum(dv.cantidad), 0) " +
            "FROM venta v " +
            "JOIN detalle_venta dv ON v.id = dv.venta_id AND v.fecha = ?1 " +
            "WHERE v.cliente_id = ?2 ",
            nativeQuery = true)
    int getCantidadProductosDeHoy(LocalDate fecha, int clienteId);
   /*
    @Query(value = "SELECT COALESCE(sum(dv.cantidad), 0)\n" +
            "FROM venta v\n" +
            "JOIN detalle_venta dv ON v.id = dv.venta_id AND v.fecha = CURRENT_DATE\n" +
            "WHERE v.cliente_id = ?1 AND NOT dv.venta_id = ?2")
    int getCantidad
*/
    @Query("select v from Venta v order by v.fecha desc")
    List<Venta> getAll();

    public void deleteVentaById(int id);

    @Query("select new com.aw.awtp5.dto.ResumenVentaDTO(v.clienteId, v. fecha, sum(dv.cantidad)) " +
            "from Venta v join DetalleVenta dv on v.id = dv.ventaId where v.id = ?1")
    public ResumenVentaDTO getById(int id);
}
