package com.strafe.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios", schema = "dbo")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioID;

    private String nombre;

    @Column(name = "CorreoElectronico", unique = true)
    private String correoElectronico; // Este campo cambia a minúscula para evitar cualquier problema de nomenclatura en el código

    @Column(name = "Contrasena")
    private String contrasena;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "RolID")
    private Long rolID;

    @Column(name = "FechaRegistro")
    private LocalDateTime fechaRegistro;

    @Column(name = "Login")
    private String login;

    // Constructores, getters y setters

    public Usuario() {}

    public Usuario(String nombre, String correoElectronico, String contrasena, String direccion, String telefono, Long rolID, LocalDateTime fechaRegistro, String login) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rolID = rolID;
        this.fechaRegistro = fechaRegistro;
        this.login = login;
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getRolID() {
        return rolID;
    }

    public void setRolID(Long rolID) {
        this.rolID = rolID;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}