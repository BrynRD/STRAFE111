package com.strafe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;  // Importa esta anotación
import java.util.List;

@Entity
@Table(name = "Categorias", schema = "dbo")  // Especifica el esquema y el nombre de la tabla
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaID")  // Asegúrate de que el nombre del ID coincide con tu tabla
    private Long categoriaid;

    @Column(name = "Nombre")  // Mapea la columna Nombre
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore  // Evita la recursión infinita en la serialización
    private List<Producto> productos;

    // Getters y setters
    public Long getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(Long categoriaid) {
        this.categoriaid = categoriaid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}