package com.strafe.controller;

import com.strafe.model.Producto;
import com.strafe.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listProductos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos";
    }

    @GetMapping("/detalle/{id}")
    public String viewProductoDetails(@PathVariable Long id, Model model) {
        System.out.println("Obteniendo detalles del producto con ID: " + id);
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
        System.out.println("Producto encontrado: " + producto.getNombre());
        model.addAttribute("producto", producto);
        return "detalleproducto";
    }
}