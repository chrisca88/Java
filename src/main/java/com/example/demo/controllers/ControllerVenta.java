package com.example.demo.controllers;

import com.example.demo.models.Venta;
import com.example.demo.repository.RepositoryVenta;
import com.example.demo.service.ServiceVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerVenta {

    @Autowired
    private ServiceVenta serviceVentaVenta;

    @GetMapping("ventas")
    public List<Venta> getVentas(){
        return serviceVentaVenta.getVentas();
    }

    @PostMapping("ingresar")
    public String post(@RequestBody Venta venta){
        serviceVentaVenta.post(venta);
        return ("La venta fue agregado exitosamente");
    }

    @PutMapping("alterar/{id}")
    public String update(@PathVariable Long id, @RequestBody Venta venta){
        serviceVentaVenta.update(id,venta);
        return ("La venta se ha modificado");
    }

    @DeleteMapping("quitar/{id}")
    public String deleteVenta(@PathVariable Long id){
        serviceVentaVenta.deleteVenta(id);
        return ("La venta ha sido eliminado");
    }
}
