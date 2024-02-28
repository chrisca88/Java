package com.example.demo.DTO;

import com.example.demo.models.Cliente;
import com.example.demo.models.DetalleVenta;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VentaDTO {

    private Long id;
    private int total;
    private Date fecha;
    private Cliente cliente;
    private Set<DetalleVentaDTO> detalleVentasDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

//    public Set<DetalleVentaDTO> getDetalleVentas() {
//        return detalleVentasDTO;
//    }
//
//    public void setDetalleVentasDTO(detalleVentasDTO) {
//        this.detalleVentasDTO = detalleVentasDTO;
//    }


    public Set<DetalleVentaDTO> getDetalleVentasDTO() {
        return detalleVentasDTO;
    }

    public void setDetalleVentasDTO(Set<DetalleVentaDTO> detalleVentasDTO) {
        this.detalleVentasDTO = detalleVentasDTO;
    }
}
