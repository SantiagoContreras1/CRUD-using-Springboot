package com.santiagocontreras.webapp.biblioteca1.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santiagocontreras.webapp.biblioteca1.model.Prestamo;
import com.santiagocontreras.webapp.biblioteca1.service.PrestamoService;
import com.santiagocontreras.webapp.biblioteca1.util.MethodType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RestController
@RequestMapping("")
public class PrestamoController {
    @Autowired
    PrestamoService prestamoService;

    // Listar
    @GetMapping("/prestamos")
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        try {
            return ResponseEntity.ok(prestamoService.listarPrestamos());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Buscar

    // Agregar
    @PostMapping("/prestamo")
    public ResponseEntity<Map<String, String>> agregarPrestamo(@RequestBody Prestamo prestamo) {
        Map<String, String> response = new HashMap<>();

        try {
            prestamoService.guardarPrestamo(prestamo,MethodType.POST);
            response.put("message", "Se creó el prestamo exitosamente!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el prestamo :[");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Editar
    @PutMapping("/prestamo")
    public ResponseEntity<Map<String, String>> editarPrestamo(@RequestParam Long id,
            @RequestBody Prestamo prestamoNew) {
        Map<String, String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamo.setCliente(prestamoNew.getCliente());
            prestamo.setEmpleado(prestamoNew.getEmpleado());
            prestamo.setFechaDeDevolucion(prestamoNew.getFechaDeDevolucion());
            prestamo.setFechaDePrestamo(prestamoNew.getFechaDePrestamo());
            prestamo.setLibros(prestamoNew.getLibros());
            prestamo.setVigencia(prestamoNew.getVigencia());

            prestamoService.guardarPrestamo(prestamo,MethodType.PUT);
            response.put("message", "Prestamo editado con éxito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el prestamo :[");
            return ResponseEntity.badRequest().body(response);

        }

    }
}
