package com.demo.controller;

import java.io.*;
import java.util.*;

public class LoginController {

    private static final String FILE_PATH = "users.csv";

    // Método para registrar un nuevo usuario
    public boolean register(String username, String password) {
        // Verificar si el archivo ya existe
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        // Comprobar si el usuario ya existe
        if (userExists(username)) {
            System.out.println("El nombre de usuario ya está registrado.");
            return false;
        }

        // Guardar el nuevo usuario en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("Usuario registrado con éxito.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para iniciar sesión con un usuario existente
    public boolean login(String username, String password) {
        File file = new File(FILE_PATH);

        // Verificar si el archivo existe
        if (!file.exists()) {
            System.out.println("No se han encontrado usuarios registrados.");
            return false;
        }

        // Leer el archivo para verificar las credenciales
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(",");
                String storedUsername = user[0];
                String storedPassword = user[1];

                // Comparar el nombre de usuario y la contraseña
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    System.out.println("Inicio de sesión exitoso.");
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Nombre de usuario o contraseña incorrectos.");
        return false;
    }

    // Verificar si un usuario ya está registrado
    private boolean userExists(String username) {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(",");
                if (user[0].equals(username)) {
                    return true; // El usuario ya existe
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        LoginController controller = new LoginController();

        // Registro de usuario
        controller.register("usuario1", "password123");

        // Intento de inicio de sesión
        controller.login("usuario1", "password123");  // Debería ser exitoso
        controller.login("usuario1", "wrongpassword");  // Debería fallar
        controller.login("usuario2", "password123");  // Debería fallar
    }
}
