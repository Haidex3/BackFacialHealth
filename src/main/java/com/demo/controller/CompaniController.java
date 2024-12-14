package com.demo.controller;

import com.demo.model.Compani;
import com.demo.Service.CompaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/companies")
public class CompaniController {

    @Autowired
    private CompaniService companiService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerCompany(@RequestBody Compani company) {
        Map<String, Object> response = new HashMap<>();

        try {
            companiService.registerCompany(company);
            response.put("status", "success");
            response.put("message", "Compañía registrada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            response.put("status", "error");
            response.put("message", "Error al registrar la compañía: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity<Map<String, Object>> getCompanyByName(@RequestParam String name) {
        Map<String, Object> response = new HashMap<>();
        try {
            Compani company = companiService.getCompanyByName(name);

            if (company == null) {
                response.put("status", "not_found");
                response.put("message", "Compañía no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("status", "success");
            response.put("data", company);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            response.put("status", "error");
            response.put("message", "Error al buscar la compañía: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAllCompanies() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Compani> companies = companiService.getAllCompanies();

            if (companies.isEmpty()) {
                response.put("status", "success");
                response.put("message", "No hay compañías registradas");
                response.put("data", companies);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            response.put("status", "success");
            response.put("data", companies);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            response.put("status", "error");
            response.put("message", "Error al obtener las compañías: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
