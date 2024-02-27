package com.example.demo.models;

import java.util.List;

public class VentaRequest {
    private Long clienteId;
    private List<ProductoCantidad> productos;

    public VentaRequest() {
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ProductoCantidad> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCantidad> productos) {
        this.productos = productos;
    }
}
