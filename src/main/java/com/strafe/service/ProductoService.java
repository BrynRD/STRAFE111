package com.strafe.service;

import com.strafe.model.Producto;
import com.strafe.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto productoDetails) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStockS(productoDetails.getStockS());
        producto.setStockM(productoDetails.getStockM());
        producto.setStockL(productoDetails.getStockL());
        producto.setStockXL(productoDetails.getStockXL());
        producto.setImagenP(productoDetails.getImagenP());
        producto.setImagenF(productoDetails.getImagenF());
        producto.setImagenL(productoDetails.getImagenL());
        producto.setCategoria(productoDetails.getCategoria());
        return productoRepository.save(producto);
    }

    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}