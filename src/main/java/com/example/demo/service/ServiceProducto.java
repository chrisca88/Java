package com.example.demo.service;

import com.example.demo.models.Producto;
import com.example.demo.repository.RepositoryProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceProducto {
    @Autowired
    private RepositoryProducto repoProd;

    public List<Producto> getProductos(){

        return repoProd.findAll();
    }

    public Producto getProductoPorId(Long id) {
        return repoProd.findById(id).orElse(null);
    }

    public void post(Producto producto){
        repoProd.save(producto);
    }

    public void update(Long id,Producto producto){
        Producto updateProducto = repoProd.findById(id).get();
        updateProducto.setNombre(producto.getNombre());
        updateProducto.setDescripcion(producto.getDescripcion());
        updateProducto.setPrecio(producto.getPrecio());
        updateProducto.setStock((producto.getStock()));
        repoProd.save(updateProducto);
    }

    public void deleteProd(Long id){
        Producto deleteProducto = repoProd.findById(id).get();
        repoProd.delete(deleteProducto);
    }

    public void actualizarProducto(Producto producto) {
        repoProd.save(producto);
    }


}
