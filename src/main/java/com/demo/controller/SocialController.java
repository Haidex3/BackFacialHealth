package com.demo.controller;

import com.demo.Service.TwitService;
import com.demo.model.Twit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/twits")
public class SocialController {

    @Autowired
    private TwitService twitService;

    @PostMapping("/createTwit")
    public ResponseEntity<Map<String, String>> createTwit(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String details = requestBody.get("details");

        Map<String, String> response = new HashMap<>();

        try {
            twitService.createTwit(username, details);
            response.put("message", "Twit creado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            response.put("error", "Error al crear el twit: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAllTwits")
    public List<Twit> getAllTwits() {
        try {
            return twitService.getAllTwits();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

