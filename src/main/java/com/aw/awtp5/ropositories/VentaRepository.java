package com.aw.awtp5.ropositories;

import com.aw.awtp5.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("VentaRepository")
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
