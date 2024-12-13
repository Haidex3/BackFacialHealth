package com.demo.controller;

import com.demo.Service.TwitService;
import com.demo.model.Twit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/twits")
public class SocialController {

    @Autowired
    private TwitService twitService;

    @PostMapping
    public String createTwit(@RequestParam String username, @RequestParam String details) {
        try {
            twitService.createTwit(username, details);
            return "Twit creado exitosamente";
        } catch (IOException e) {
            return "Error al crear el twit: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Twit> getAllTwits() {
        try {
            return twitService.getAllTwits();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

