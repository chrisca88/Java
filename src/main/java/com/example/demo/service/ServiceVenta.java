package com.example.demo.service;

import com.example.demo.DTO.DetalleVentaDTO;
import com.example.demo.DTO.VentaDTO;
import com.example.demo.models.*;
import com.example.demo.repository.RepositoryVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class ServiceVenta {

    @Autowired
    private RepositoryVenta repoVenta;
    @Autowired
    private ServiceCliente serviceCliente;
    @Autowired
    private ServiceProducto serviceProducto;


    public List<VentaDTO> getVentas() {
        return crearVentasDTO(this.repoVenta.findAll());
    }

    public Venta getVentaPorId(Long id) {
        return repoVenta.findById(id).orElse(null);
    }

    public void crearVenta(VentaRequest ventaRequest) {
        Venta venta = new Venta();

        // Establecer el cliente de la venta
        Cliente cliente = serviceCliente.getClienteId(ventaRequest.getClienteId());
        venta.setCliente(cliente);

        Date fechaActual = ObtenerFechaActual.obtenerFechaActual();
        venta.setFecha(fechaActual);

        // Crear detalles de venta y asociarlos a la venta
        Set<DetalleVenta> detallesVenta = new HashSet<>();
        for (ProductoCantidad productoCantidad : ventaRequest.getProductos()) {
            Producto producto = serviceProducto.getProductoPorId(productoCantidad.getProductoId());
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidadProducto(productoCantidad.getCantidad());
            detalleVenta.setPrecioProducto(producto.getPrecio());
            detallesVenta.add(detalleVenta);
        }
        venta.setDetalleVentas(detallesVenta);

        // Calcular el total de la venta
        int totalVenta = calcularTotalVenta(detallesVenta);
        venta.setTotal(totalVenta);

        // Guardar la venta en la base de datos
        repoVenta.save(venta);
    }

    private int calcularTotalVenta(Set<DetalleVenta> detallesVenta) {
        int total = 0;
        for (DetalleVenta detalleVenta : detallesVenta) {
            total += detalleVenta.getCantidadProducto() * detalleVenta.getPrecioProducto();
        }
        return total;
    }


    private List<VentaDTO> crearVentasDTO(List<Venta> ventas) {
        return ventas.stream().map(this::crearVentaDTO).collect(Collectors.toList());
    }

    private VentaDTO crearVentaDTO(Venta venta) {
        VentaDTO ventadto = new VentaDTO();
        ventadto.setId(venta.getId());
        ventadto.setFecha(venta.getFecha());
        ventadto.setTotal(venta.getTotal());
        ventadto.setCliente(venta.getCliente());
        ventadto.setDetalleVentas(crearDetalleVentasDTO(venta.getDetalleVentas()));
        return ventadto;
    }

    private Set<DetalleVentaDTO> crearDetalleVentasDTO(Set<DetalleVenta> detalleVentas) {
        Set<DetalleVentaDTO> ventadtos = new HashSet<>();
        for (DetalleVenta detalleVenta : detalleVentas) {
            DetalleVentaDTO ventadto = new DetalleVentaDTO();
            ventadto.setId(detalleVenta.getId());
            ventadto.setCantidadProducto(detalleVenta.getCantidadProducto());
            ventadto.setDescripcion(detalleVenta.getProducto().getNombre());
            ventadto.setPrecioProducto(detalleVenta.getPrecioProducto());
            ventadtos.add(ventadto);
        }
        return ventadtos;
    }

}
