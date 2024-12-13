package com.demo.controller;

import java.io.*;
import java.util.*;

public class LoginController {

    private static final String FILE_PATH = "users.csv";

    public boolean register(String username, String password) {
        File file = new File(FILE_PATH);
        System.out.println(username + " " + password);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        if (userExists(username)) {
            System.out.println("El nombre de usuario ya está registrado.");
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + password);  // No añadimos comillas aquí
            writer.newLine();
            System.out.println("Usuario registrado con éxito.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String username, String password) {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("No se han encontrado usuarios registrados.");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Eliminar espacios y saltos de línea extra
                String[] user = line.split(",");
                if (user.length < 2) {
                    continue; // Si la línea no contiene ambos campos, saltarla
                }
                String storedUsername = user[0].trim();
                String storedPassword = user[1].trim();

                if (storedUsername.equals(username.trim()) && storedPassword.equals(password.trim())) {
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
                line = line.trim();
                String[] user = line.split(",");
                if (user.length < 2) {
                    continue;
                }
                if (user[0].trim().equals(username.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
