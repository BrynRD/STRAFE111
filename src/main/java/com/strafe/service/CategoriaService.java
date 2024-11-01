package com.strafe.service;

import com.strafe.model.Categoria;
import com.strafe.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorías
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    // Buscar una categoría por ID
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    // Guardar una categoría (si fuera necesario en el futuro)
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    // Eliminar una categoría por ID (si fuera necesario en el futuro)
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
