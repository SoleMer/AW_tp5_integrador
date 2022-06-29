package com.aw.awtp5.ropositories;

import com.aw.awtp5.dto.MontoTotalClientesDTO;
import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.DetalleVentaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("DetalleVentaRepository")
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaPK> {

    @Query("SELECT c, SUM(monto) FROM Cliente c ")
    public MontoTotalClientesDTO getReporteVentasClientes();
}
