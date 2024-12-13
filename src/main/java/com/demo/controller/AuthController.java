package com.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.demo.controller.LoginController;
import com.demo.model.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginController loginController;

    public AuthController() {
        this.loginController = new LoginController();
    }

    // Método para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean success = loginController.register(user.getUsername(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } else {
            return ResponseEntity.status(400).body("Error al registrar el usuario");
        }
    }

    // Método para hacer login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean success = loginController.login(user.getUsername(), user.getPassword());
        if (success) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
