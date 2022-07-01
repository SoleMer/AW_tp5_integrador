package com.aw.awtp5.ropositories;

import com.aw.awtp5.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("ProductoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    /*
    select p.nombre, p.precio, p.stock from producto p
    where p.id in (select distinct dv.producto_id
    from detalle_venta dv
    group by dv.producto_id
    order by sum(dv.cantidad) desc)
    limit 1;

    @Query("select new Producto(p.nombre, p.stock, p.precio) " +
            "from Producto p where p.id in (select distinct dv.producto_id from DetalleVenta dv " +
            "group by dv.producto_id order by sum(dv.cantidad) desc) " +
            "limit 1")
    Producto getMasVendido();


    select p.nombre, p.stock, p.precio, sum(dv.cantidad) as total
    from detalle_venta dv
    join producto p on p.id = dv.producto_id
    group by dv.producto_id
    order by total desc
    limit 1;
     */

    @Query("select new Producto(p.id, p.nombre, p.stock, p.precio) " +
            "from DetalleVenta dv " +
            "join Producto p on dv.productoId = p.id " +
            "group by dv.productoId " +
            "order by sum(dv.cantidad) desc")
    List<Producto> getMasVendido();



    Producto findProductoById(Integer id);
}
