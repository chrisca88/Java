package com.example.demo.controllers;

import com.example.demo.DTO.DetalleVentaDTO;
import com.example.demo.DTO.VentaDTO;
import com.example.demo.models.*;
import com.example.demo.service.ServiceCliente;
import com.example.demo.service.ServiceProducto;
import com.example.demo.service.ServiceVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ventas")
public class ControllerVenta {

    @Autowired
    private ServiceVenta serviceVentaVenta;

    @Autowired
    private ServiceCliente serviceCliente;

    @Autowired
    private ServiceProducto serviceProducto;

    @GetMapping("/listar")
    public List<VentaDTO> getVentas(){
        return this.serviceVentaVenta.getVentas();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<VentaDTO> getVentaById(@PathVariable Long id) {
        Venta venta = serviceVentaVenta.getVentaPorId(id);
        if (venta != null) {
            VentaDTO ventaDTO = crearVentaDTO(venta);
            return ResponseEntity.ok(ventaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private VentaDTO crearVentaDTO(Venta venta) {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setId(venta.getId());
        ventaDTO.setTotal(venta.getTotal());
        ventaDTO.setFecha(venta.getFecha());
        ventaDTO.setCliente(venta.getCliente());

        Set<DetalleVenta> detalleVentas = venta.getDetalleVentas();
        if (detalleVentas != null && !detalleVentas.isEmpty()) {
            Set<DetalleVentaDTO> detalleVentaDTOs = new HashSet<>();
            for (DetalleVenta detalleVenta : detalleVentas) {
                DetalleVentaDTO detalleVentaDTO = new DetalleVentaDTO();
                detalleVentaDTO.setId(detalleVenta.getId());
                detalleVentaDTO.setCantidadProducto(detalleVenta.getCantidadProducto());
                detalleVentaDTO.setPrecioProducto(detalleVenta.getPrecioProducto());
                detalleVentaDTO.setDescripcion(detalleVenta.getDescripcion());

                detalleVentaDTOs.add(detalleVentaDTO);
            }
            ventaDTO.setDetalleVentas(detalleVentaDTOs);
        }
        return ventaDTO;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearVenta(@RequestBody VentaRequest ventaRequest) {
        // Verificar si el cliente existe
        Cliente cliente = serviceCliente.getClienteId(ventaRequest.getClienteId());
        if (cliente == null) {
            return ResponseEntity.badRequest().body("El cliente no existe");
        }

        // Verificar si cada producto existe y tiene suficiente stock
        for (ProductoCantidad productoCantidad : ventaRequest.getProductos()) {
            Producto producto = serviceProducto.getProductoPorId(productoCantidad.getProductoId());
            if (producto == null) {
                return ResponseEntity.badRequest().body("El producto con ID " + productoCantidad.getProductoId() + " no existe");
            }
            if (producto.getStock() < productoCantidad.getCantidad()) {
                return ResponseEntity.badRequest().body("El producto " + producto.getNombre() + " no tiene suficiente stock");
            }
        }

        // Si todas las comprobaciones son exitosas, crear la venta
        serviceVentaVenta.crearVenta(ventaRequest);

        return ResponseEntity.ok("La venta ha sido creada exitosamente");
    }

}
