package com.strafe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Productos", schema = "dbo")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductoID")
    private Long id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "Precio", nullable = false)
    private Double precio;

    @Column(name = "StockS", nullable = false)
    private Integer stockS;

    @Column(name = "StockM", nullable = false)
    private Integer stockM;

    @Column(name = "StockL", nullable = false)
    private Integer stockL;

    @Column(name = "StockXL", nullable = false)
    private Integer stockXL;

    @Column(name = "ImagenP")
    private String imagenP;

    @Column(name = "ImagenF")
    private String imagenF;

    @Column(name = "ImagenL")
    private String imagenL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoriaID", nullable = false)
    private Categoria categoria;

    // Constructor vacío requerido por JPA
    public Producto() {
    }

    // Constructor con todos los campos (opcional)
    public Producto(String nombre, String descripcion, Double precio, Integer stockS, Integer stockM, Integer stockL, Integer stockXL, Categoria categoria, String imagenP, String imagenF, String imagenL) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockS = stockS;
        this.stockM = stockM;
        this.stockL = stockL;
        this.stockXL = stockXL;
        this.categoria = categoria;
        this.imagenP = imagenP;
        this.imagenF = imagenF;
        this.imagenL = imagenL;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStockS() {
        return stockS;
    }

    public void setStockS(Integer stockS) {
        this.stockS = stockS;
    }

    public Integer getStockM() {
        return stockM;
    }

    public void setStockM(Integer stockM) {
        this.stockM = stockM;
    }

    public Integer getStockL() {
        return stockL;
    }

    public void setStockL(Integer stockL) {
        this.stockL = stockL;
    }

    public Integer getStockXL() {
        return stockXL;
    }

    public void setStockXL(Integer stockXL) {
        this.stockXL = stockXL;
    }

    public String getImagenP() {
        return imagenP;
    }

    public void setImagenP(String imagenP) {
        this.imagenP = imagenP;
    }

    public String getImagenF() {
        return imagenF;
    }

    public void setImagenF(String imagenF) {
        this.imagenF = imagenF;
    }

    public String getImagenL() {
        return imagenL;
    }

    public void setImagenL(String imagenL) {
        this.imagenL = imagenL;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Método toString (opcional, útil para depuración)
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stockS=" + stockS +
                ", stockM=" + stockM +
                ", stockL=" + stockL +
                ", stockXL=" + stockXL +
                ", imagenP='" + imagenP + '\'' +
                ", imagenF='" + imagenF + '\'' +
                ", imagenL='" + imagenL + '\'' +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "N/A") +
                '}';
    }

    // Equals y HashCode (opcional, basado en el ID)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        return id != null ? id.equals(producto.id) : producto.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}