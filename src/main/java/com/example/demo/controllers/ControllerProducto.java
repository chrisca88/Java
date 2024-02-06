package com.example.demo.controllers;

import com.example.demo.models.Producto;
import com.example.demo.service.ServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerProducto {

    @Autowired
    private ServiceProducto serviceProducto;

    @GetMapping("productos")
    public List<Producto> getProductos() {

        return serviceProducto.getProductos();
    }

    @PostMapping("agregar")
    public String post(@RequestBody Producto producto){
        serviceProducto.post(producto);
        return ("El producto fue agregado exitosamente");
    }

    @PutMapping("cambiar/{id}")
    public String update(@PathVariable Long id, @RequestBody Producto producto){
        serviceProducto.update(id,producto);
        return ("El producto se ha modificado");
    }

    @DeleteMapping("borrar/{id}")
    public String deleteProd(@PathVariable Long id){
        serviceProducto.deleteProd(id);
        return ("El producto ha sido eliminado");
    }
}
