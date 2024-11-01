package com.strafe.controller;

import com.strafe.model.Producto;
import com.strafe.service.ProductoService;
import com.strafe.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String showAdminPage() {
        return "admin"; // Muestra solo el dashboard principal
    }

    @GetMapping("/productos")
    public String showAdminProductosPage(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "adminp"; // Muestra la página de productos
    }

    @GetMapping("/productos/nuevo")
    public String showCreateProductoForm(Model model) {
        logger.info("Accediendo a la ruta /admin/productos/nuevo");
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        return "nuevoproducto";
    }

    @PostMapping("/productos/guardar")
    public String createProducto(@ModelAttribute Producto producto,
                                 @RequestParam(value = "imagenPrincipalFile", required = false) MultipartFile imagenPrincipalFile,
                                 @RequestParam(value = "imagenFFile", required = false) MultipartFile imagenFFile,
                                 @RequestParam(value = "imagenLFile", required = false) MultipartFile imagenLFile,
                                 Model model) {
        // Validación de campos del producto
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            model.addAttribute("errorMessage", "El nombre del producto es obligatorio.");
            return "nuevoproducto"; // Regresar al formulario con el mensaje de error
        }

        try {
            if (!imagenPrincipalFile.isEmpty()) {
                String imagenUrl = guardarImagen(imagenPrincipalFile);
                producto.setImagenP("/images/" + imagenUrl);
            }
            if (!imagenFFile.isEmpty()) {
                String imagenFUrl = guardarImagen(imagenFFile);
                producto.setImagenF("/images/" + imagenFUrl);
            }
            if (!imagenLFile.isEmpty()) {
                String imagenLUrl = guardarImagen(imagenLFile);
                producto.setImagenL("/images/" + imagenLUrl);
            }

            productoService.saveProducto(producto);
            logger.info("Producto guardado exitosamente: {}", producto.getNombre());
        } catch (IOException e) {
            logger.error("Error al guardar la imagen: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al guardar la imagen: " + e.getMessage());
            return "nuevoproducto";
        } catch (Exception e) {
            logger.error("Error al guardar el producto: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al guardar el producto: " + e.getMessage());
            return "nuevoproducto";
        }
        return "redirect:/admin/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String showEditProductoForm(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.findAll());
        logger.info("Mostrando formulario para editar el producto: {}", producto.getNombre());
        return "editar";
    }

    @PostMapping("/productos/actualizar/{id}")
    public String updateProducto(@PathVariable Long id,
                                 @ModelAttribute Producto producto,
                                 @RequestParam(value = "imagenPrincipalFile", required = false) MultipartFile imagenPrincipal,
                                 @RequestParam(value = "imagenFFile", required = false) MultipartFile imagenF,
                                 @RequestParam(value = "imagenLFile", required = false) MultipartFile imagenL,
                                 Model model) {
        producto.setId(id);
        Producto productoExistente = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));

        // Manejo de la imagen principal
        if (!imagenPrincipal.isEmpty()) {
            try {
                String imagenUrl = guardarImagen(imagenPrincipal);
                producto.setImagenP("/images/" + imagenUrl);
            } catch (IOException e) {
                model.addAttribute("errorMessage", "Error al guardar la imagen principal: " + e.getMessage());
                return "editar";
            }
        } else {
            producto.setImagenP(productoExistente.getImagenP()); // Conservar imagen existente
        }

        // Manejo de la imagen frontal
        if (!imagenF.isEmpty()) {
            try {
                String imagenUrl = guardarImagen(imagenF);
                producto.setImagenF("/images/" + imagenUrl);
            } catch (IOException e) {
                model.addAttribute("errorMessage", "Error al guardar la imagen frontal: " + e.getMessage());
                return "editar";
            }
        } else {
            producto.setImagenF(productoExistente.getImagenF());
        }

        // Manejo de la imagen lateral
        if (!imagenL.isEmpty()) {
            try {
                String imagenUrl = guardarImagen(imagenL);
                producto.setImagenL("/images/" + imagenUrl);
            } catch (IOException e) {
                model.addAttribute("errorMessage", "Error al guardar la imagen lateral: " + e.getMessage());
                return "editar";
            }
        } else {
            producto.setImagenL(productoExistente.getImagenL());
        }

        // Guardar el producto
        try {
            productoService.saveProducto(producto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al guardar el producto: " + e.getMessage());
            return "editar";
        }

        return "redirect:/admin/productos";
    }

    @PostMapping("/productos/eliminar/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
        productoService.deleteProducto(id);
        logger.info("Producto eliminado exitosamente: {}", producto.getNombre());
        return "redirect:/admin/productos";
    }

    private String guardarImagen(MultipartFile imagen) throws IOException {
        Path directorioImagenes = Paths.get("C:/Users/bryn/Desktop/Strafe O/Strafe/src/main/resources/static/images");
        if (!Files.exists(directorioImagenes)) {
            Files.createDirectories(directorioImagenes);
        }

        // Obtener el nombre original de la imagen
        String nombreArchivoOriginal = imagen.getOriginalFilename();
        if (nombreArchivoOriginal == null) {
            throw new IOException("El archivo no tiene un nombre válido.");
        }

        // Usa el nombre original del archivo
        String nombreArchivo = nombreArchivoOriginal;

        Path rutaArchivo = directorioImagenes.resolve(nombreArchivo);
        Files.copy(imagen.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

        return nombreArchivo; // Retorna solo el nombre original
    }
}
