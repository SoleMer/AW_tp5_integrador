package com.aw.awtp5.ropositories;

import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.DetalleVentaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("DetalleVentaRepository")
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaPK> {

}
