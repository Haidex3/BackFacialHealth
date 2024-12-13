package com.demo.Service;

import com.demo.model.Twit;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TwitService {
    private static final String FILE_PATH = "twits.csv";

    public List<Twit> getAllTwits() throws IOException {
        List<Twit> twits = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return twits;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    twits.add(new Twit(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error al leer el archivo de twits.");
        }

        return twits;
    }

    public void createTwit(String username, String details) throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("No se pudo crear el archivo de twits.");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String record = username + "," + details;
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error al escribir en el archivo de twits.");
        }
    }
}
