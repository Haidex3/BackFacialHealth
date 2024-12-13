package com.demo.Service;

import com.demo.model.Twit;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TwitService {
    private static final String FILE_PATH = "twits.csv";

    public List<Twit> getAllTwits() throws IOException {
        List<Twit> twits = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                twits.add(new Twit(line[0], line[1]));
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return twits;
    }

    public void createTwit(String username, String details) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH, true))) {
            String[] record = { username, details };
            writer.writeNext(record);
        }
    }
}
