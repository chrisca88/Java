package com.example.demo.service;

import com.example.demo.models.Venta;
import com.example.demo.repository.RepositoryVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class ServiceVenta {

    @Autowired
    private RepositoryVenta repoVenta;

    public List<Venta> getVentas(){

        return repoVenta.findAll();
    }

    public void post(Venta venta){
        repoVenta.save(venta);
    }

    public void update(Long id,Venta venta){
        Venta updateVenta = repoVenta.findById(id).get();
        updateVenta.setProducto(venta.getProducto());
        updateVenta.setTotal(venta.getTotal());
        repoVenta.save(updateVenta);
    }

    public void deleteVenta(Long id){
        Venta deleteVenta = repoVenta.findById(id).get();
        repoVenta.delete(deleteVenta);

    }
}
