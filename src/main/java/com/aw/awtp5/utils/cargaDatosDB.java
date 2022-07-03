package com.aw.awtp5.utils;

import com.aw.awtp5.dto.DetalleVentaDTO;
import com.aw.awtp5.entities.Cliente;
import com.aw.awtp5.entities.DetalleVenta;
import com.aw.awtp5.entities.Producto;
import com.aw.awtp5.ropositories.ClienteRepository;
import com.aw.awtp5.ropositories.ProductoRepository;
import com.aw.awtp5.services.VentaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class cargaDatosDB {

    long DNI = 1000;
    int QUANTITY = 50;

    @Bean
    public CommandLineRunner initDB(ProductoRepository productos, ClienteRepository clientes, VentaService venta) {
        return args -> {

            // insertar 5 clientes
            for (int i = 0; i < 5; i++) {
                Producto p = new Producto("Producto " + i, 100, 100);
                productos.save(p);
            }

            // insertar 5 productos
            for (int i = 0; i < 5; i++) {
                Cliente c = new Cliente("Cliente " + i);
                clientes.save(c);
            }

            // lista clientes añadidos
            ArrayList<Cliente> listaClientes = new ArrayList<>();
            listaClientes = (ArrayList<Cliente>) clientes.findAll();

            // insertar ventas random dentro de los parametros aceptados de cantidad de unidades diarias
            for(int i = 0; i < listaClientes.size(); i++) {

                // lista productos añadidos
                ArrayList<Producto> listaProductos = new ArrayList<>();
                listaProductos = (ArrayList<Producto>) productos.findAll();

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
                    venta.save(d);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
          }
        };
    }
}


