package com.demo.controller;

import java.io.*;

public class LoginController {

    private static final String FILE_PATH = "users.csv";

    public boolean register(String username, String password, String email, int age, String gender) {
        File file = new File(FILE_PATH);
        System.out.println("Username: " + username + " | Password: " + password + " | Email: " + email + " | Age: " + age + " | Gender: " + gender);

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
            writer.write(username + "," + password + "," + email + "," + age + "," + gender);
            writer.newLine();
            System.out.println("Usuario registrado con éxito.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public String[] login(String username, String password) {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("No se han encontrado usuarios registrados.");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] user = line.split(",");
                if (user.length < 2) {
                    continue;
                }
                String storedUsername = user[0].trim();
                String storedPassword = user[1].trim();

                if (storedUsername.equals(username.trim()) && storedPassword.equals(password.trim())) {
                    System.out.println("Inicio de sesión exitoso.");
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Nombre de usuario o contraseña incorrectos.");
        return null; // Retorna null si no se encuentra una coincidencia
    }


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
