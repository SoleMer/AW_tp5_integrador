package com.aw.awtp5.utils;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.entities.Cliente;
import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.entities.Venta;
import com.aw.awtp5.ropositories.ClienteRepository;
import com.aw.awtp5.ropositories.ProductoRepository;
import com.aw.awtp5.services.VentaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase utilizada para la carga aleatoria de elementos en la base de datos
 * @author arana-marsico-merino
 * @version 1.0
 */
@Configuration
public class cargaDatosDB {

    /**
     * Crea productos y clientes y les asigna compras aleatorias
     * @param productoRepository
     * @param clienteRepository
     * @param ventaService
     * @return
     */
    @Bean
    public CommandLineRunner initDB(ProductoRepository productoRepository, ClienteRepository clienteRepository, VentaService ventaService) {
        return args -> {

            // insertar 5 productos
            for (int i = 0; i < 5; i++) {
                Producto p = new Producto("Producto " + i, 100, 100);
                productoRepository.save(p);
            }

            // insertar 5 clientes
            for (int i = 0; i < 5; i++) {
                Cliente c = new Cliente("Cliente " + i);
                clienteRepository.save(c);
            }

            // lista clientes añadidos
            ArrayList<Cliente> listaClientes = new ArrayList<>();
            listaClientes = (ArrayList<Cliente>) clienteRepository.findAll();

            // insertar ventas random dentro de los parametros aceptados de cantidad de unidades diarias
            for(int i = 0; i < listaClientes.size(); i++) {

                // lista productos añadidos
                ArrayList<Producto> listaProductos = new ArrayList<>();
                listaProductos = (ArrayList<Producto>) productoRepository.findAll();

                // lista de detalles de venta aleatorios
                ArrayList<DetalleVenta> aux = new ArrayList<>();
                int cantidadRandom = 0;
                int contador = 0;

                // añadir productos a la venta hasta completar 3 unidades
                while(contador < 3) {
                    cantidadRandom = (int) (Math.random() * (3 - contador)) + 1;
                    Producto productoRandom = listaProductos.remove((int) (Math.random() * listaProductos.size()));
                    DetalleVenta dv = new DetalleVenta();
                    dv.setCantidad(cantidadRandom);
                    dv.setProductoId(productoRandom.getId());
                    aux.add(dv);
                    contador += cantidadRandom;
                }
                DetalleVentaDTO d = new DetalleVentaDTO(listaClientes.get(i).getId(), aux);
                try {
                    // añadir venta y añadir los detalles de cada producto y las unidades de la venta
                    ventaService.save(d);
                    Venta venta = ventaService.getUltimaInsertada();
                    venta.setFecha(this.getFechaRandom());
                    ventaService.update(venta);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
          }
        };
    }

    /**
     * Obtiene una fecha random entre 1/1/2020 y el 30/6/2022
     * @return
     */
    public LocalDate getFechaRandom() {
        long minDay = LocalDate.of(2020, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 6, 30).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }
}


