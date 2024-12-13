package com.demo.Service;

import com.demo.model.Compani;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompaniService {
    private static final String FILE_PATH = "companies.csv";

    public Compani getCompanyByName(String name) throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6 && parts[0].trim().equalsIgnoreCase(name)) {
                    return new Compani(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim()
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error al leer el archivo de compañías.");
        }

        return null;
    }

    public void registerCompany(Compani company) throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("No se pudo crear el archivo de compañías.");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Ahora se incluyen los 6 campos (nombre, dirección, redes sociales y URL de la imagen)
            String record = String.join(",",
                    company.getCompanyName(),
                    company.getCompanyAddress(),
                    company.getCompanyInstagramUrl(),
                    company.getCompanyFacebookUrl(),
                    company.getCompanyTwitterUrl(),
                    company.getCompanyImageUrl()  // Se agrega el campo de la imagen
            );
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error al escribir en el archivo de compañías.");
        }
    }

    public List<Compani> getAllCompanies() throws IOException {
        List<Compani> companies = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return companies;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Ahora se esperan 6 partes (5 URL + 1 imagen)
                if (parts.length == 6) {
                    companies.add(new Compani(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim()  // Se agrega el campo de la imagen
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error al leer el archivo de compañías.");
        }

        return companies;
    }
}
