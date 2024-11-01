package com.strafe.controller;

import com.strafe.model.Usuario;
import com.strafe.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/registro")
    public String registerUser(@RequestParam("username") String nombre,
                               @RequestParam("email") String correoElectronico,
                               @RequestParam("password") String contrasena,
                               Model model) {
        if (usuarioService.findByCorreoElectronico(correoElectronico) != null) {
            model.addAttribute("error", "Correo electrónico ya registrado");
            return "register";
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setContrasena(contrasena);
        usuario.setFechaRegistro(LocalDateTime.now());

        usuarioService.saveUsuario(usuario);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String correoElectronico,
                            @RequestParam("password") String contrasena,
                            Model model) {
        Usuario usuario = usuarioService.findByCorreoElectronico(correoElectronico);
        if (usuario == null || !usuario.getContrasena().equals(contrasena)) {
            model.addAttribute("error", "Correo electrónico o contraseña incorrectos");
            return "login";
        }
        return "redirect:/admin";
    }
}