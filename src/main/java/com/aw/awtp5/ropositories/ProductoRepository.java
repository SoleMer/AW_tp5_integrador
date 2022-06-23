package com.aw.awtp5.ropositories;

import com.aw.awtp5.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository("ProductoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    //   @Query("")
    //   public abstract List<Producto> getAll();
}
