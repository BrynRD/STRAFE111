package com.strafe.service;

import com.strafe.model.Producto;
import com.strafe.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    private final Path rootLocation = Paths.get("src/main/resources/static/uploads");

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto save(Producto producto, MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
            producto.setImagenP("/uploads/" + filename);  // Establece la nueva URL de la imagen principal
        }

        // Guarda el producto en la base de datos (con o sin imagen)
        return productoRepository.save(producto);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public void update(Producto producto) {
        Producto existingProducto = productoRepository.findById(producto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + producto.getId()));
        existingProducto.setNombre(producto.getNombre());
        existingProducto.setDescripcion(producto.getDescripcion());
        existingProducto.setPrecio(producto.getPrecio());
        existingProducto.setStockS(producto.getStockS());
        existingProducto.setStockM(producto.getStockM());
        existingProducto.setStockL(producto.getStockL());
        existingProducto.setStockXL(producto.getStockXL());
        existingProducto.setImagenP(producto.getImagenP());
        existingProducto.setImagenF(producto.getImagenF());
        existingProducto.setImagenL(producto.getImagenL());
        existingProducto.setCategoria(producto.getCategoria());
        productoRepository.save(existingProducto);
    }
}