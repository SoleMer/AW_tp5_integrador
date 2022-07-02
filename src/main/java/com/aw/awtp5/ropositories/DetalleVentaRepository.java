package com.aw.awtp5.ropositories;

import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.DetalleVentaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio encargado de las transacciones con la BBDD correspondientes a la tabla detalle_venta
 * @author arana-marsico-merino
 * @version 1.0
 */
@Repository("DetalleVentaRepository")
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaPK> {


    @Query(value = "DELETE FROM detalle_venta dv WHERE dv.venta_id = ?1", nativeQuery = true)
    public void deleteDetalleVentaByVentaId(int ventaId);

    public void deleteAllByVentaId(int ventaId);


}
