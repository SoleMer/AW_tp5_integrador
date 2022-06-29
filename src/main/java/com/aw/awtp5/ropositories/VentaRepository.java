package com.aw.awtp5.ropositories;

import com.aw.awtp5.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("VentaRepository")
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query(value = "SELECT COALESCE(sum(dv.cantidad), 0) " +
            "FROM venta v " +
            "JOIN detalle_venta dv ON v.id = dv.venta_id " +
            "WHERE v.cliente_id = ?1 ",
            nativeQuery = true)
    public int getCantidadProductosDeHoy(int clienteId);
}
