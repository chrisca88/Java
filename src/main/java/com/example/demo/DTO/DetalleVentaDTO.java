package com.example.demo.DTO;

import java.util.Set;

public class DetalleVentaDTO {

    private Long id;
    private int cantidadProducto;
    private float precioProducto;
    private String descripcion;
    private Set<DetalleVentaDTO> detalleventa;
    private Long idProducto;
    private String nombreProducto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<DetalleVentaDTO> getDetalleventa() {
        return detalleventa;
    }

    public void setDetalleventa(Set<DetalleVentaDTO> detalleventa) {
        this.detalleventa = detalleventa;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
