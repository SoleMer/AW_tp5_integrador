package com.aw.awtp5.ropositories;

import com.aw.awtp5.dto.ClienteGastoDTO;
import com.aw.awtp5.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ClienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /*
    select c.nombre_apellido, (sum(p.precio)*sum(dv.cantidad)) as totalCompras from cliente c
LEFT JOIN venta v on c.id = v.cliente_id
left join detalle_venta dv on v.id = dv.venta_id
left join producto p on dv.producto_id = p.id
group by c.id;

INTENTO DE HACERLO SIN DTO PARA NO ABUSAR
    @Query("select new HashMap<>(c.nombreApellido, (sum(p.precio)*sum(dv.cantidad)) as totalCompras from Cliente c left join Venta v on Cliente.id left join DetalleVenta dv on v.id = dv.ventaId left join Producto p on dv.productoId = p.id group by c.id"));
    HashMap<String,Integer> getGastos();

    */

    @Query("select new com.aw.awtp5.dto.ClienteGastoDTO(c.nombreApellido, sum(p.precio * dv.cantidad)) "
            +"from Cliente c "
            +"left join Venta v on c.id = v.clienteId "
            +"left join DetalleVenta dv on v.id = dv.ventaId "
            +"left join Producto p on dv.productoId = p.id "
            +"group by c.id")
    List<ClienteGastoDTO> getTotalCompras();

}
