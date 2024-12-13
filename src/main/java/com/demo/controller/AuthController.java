package com.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.demo.controller.LoginController;
import com.demo.model.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginController loginController;

    public AuthController() {
        this.loginController = new LoginController();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        boolean success = loginController.register(user.getUsername(), user.getPassword(), user.getEmail(), user.getAge(), user.getGender());

        Map<String, Object> response = new HashMap<>();

        if (success) {
            response.put("message", "Usuario registrado exitosamente");
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Error al registrar el usuario");
            response.put("status", "error");
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        boolean success = loginController.login(user.getUsername(), user.getPassword());

        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("status", "success");
            response.put("message", "Inicio de sesión exitoso");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Credenciales incorrectas");
            return ResponseEntity.status(401).body(response);
        }
    }

}
