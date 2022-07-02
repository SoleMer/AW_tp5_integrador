package com.aw.awtp5.ropositories;

import com.aw.awtp5.dto.ClienteGastoDTO;
import com.aw.awtp5.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio encargado de las transacciones con la BBDD correspondientes a la tabla cliente
 * @author arana-marsico-merino
 * @version 1.0
 */
@Repository("ClienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * Obtiene el nombre completo de cada cliente y el total de gastos que ha realizado
     * @return ClienteGastoDTO
     */
    @Query("select new com.aw.awtp5.dto.ClienteGastoDTO(c.nombreApellido, sum(p.precio * dv.cantidad)) "
            +"from Cliente c "
            +"left join Venta v on c.id = v.clienteId "
            +"left join DetalleVenta dv on v.id = dv.ventaId "
            +"left join Producto p on dv.productoId = p.id "
            +"group by c.id")
    List<ClienteGastoDTO> getTotalCompras();

    public Cliente findClienteById(int id);
}
