package com.strafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Ruta para la página de inicio
    @GetMapping("/")
    public String home() {
        return "index"; // Devuelve index.html
    }



}
