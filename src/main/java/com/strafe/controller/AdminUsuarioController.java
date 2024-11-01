package com.strafe.controller;

import com.strafe.model.Usuario;
import com.strafe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String mostrarFormulario(Model model) {
        // Carga todos los usuarios desde la base de datos
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "admin/usuarios";
    }

    @PostMapping
    public String agregarUsuario(@ModelAttribute Usuario usuario) {
        // Guarda al nuevo usuario en la base de datos
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/editar/{usuarioID}")
    public String mostrarFormularioEdicion(@PathVariable Long usuarioID, Model model) {
        // Encuentra al usuario por su ID
        Usuario usuario = usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + usuarioID));
        model.addAttribute("usuario", usuario);
        return "admin/editarUsuario";
    }

    @PostMapping("/actualizar/{usuarioID}")
    public String actualizarUsuario(@PathVariable Long usuarioID, @ModelAttribute Usuario usuarioActualizado) {
        // Encuentra al usuario por su ID y actualiza los datos
        Usuario usuario = usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + usuarioID));

        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setCorreoElectronico(usuarioActualizado.getCorreoElectronico());
        usuario.setContrasena(usuarioActualizado.getContrasena());
        usuario.setDireccion(usuarioActualizado.getDireccion());
        usuario.setTelefono(usuarioActualizado.getTelefono());
        usuario.setRolID(usuarioActualizado.getRolID());
        usuario.setFechaRegistro(usuarioActualizado.getFechaRegistro());
        usuario.setLogin(usuarioActualizado.getLogin());

        usuarioRepository.save(usuario);

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/eliminar/{usuarioID}")
    public String eliminarUsuario(@PathVariable Long usuarioID) {
        // Elimina al usuario por su ID
        usuarioRepository.deleteById(usuarioID);
        return "redirect:/admin/usuarios";
    }
}