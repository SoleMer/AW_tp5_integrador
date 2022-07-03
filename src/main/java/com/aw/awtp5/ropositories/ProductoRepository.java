package com.aw.awtp5.ropositories;

import com.aw.awtp5.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio encargado de las transacciones con la BBDD correspondientes a la tabla producto
 * @author arana-marsico-merino
 * @version 1.0
 */
@Repository("ProductoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    /**
     * Obtiene la información de el Producto del cual la suma de todas las cantidades vendidas sea superior a la de los demás productos
     * @return
     */
    @Query("select new Producto(p.id, p.nombre, p.stock, p.precio) " +
            "from DetalleVenta dv " +
            "join Producto p on dv.productoId = p.id " +
            "group by dv.productoId " +
            "order by sum(dv.cantidad) desc")
    List<Producto> getMasVendido();

    Producto findProductoById(Integer id);
}
