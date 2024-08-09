package com.santiagocontreras.webapp.biblioteca1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiagocontreras.webapp.biblioteca1.model.Libro;
import com.santiagocontreras.webapp.biblioteca1.service.LibroService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@Controller
@RestController
@RequestMapping(value = "")
public class LibroController {
    
    @Autowired
    LibroService libroService;

    //Listar
    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> listarLibros() {
        try {
            return ResponseEntity.ok(libroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Buscar
    @GetMapping("/libro")
    public ResponseEntity<Libro> buscarLibro(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(libroService.buscarLibroPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    //Agregar
    @PostMapping("/libro")
    public ResponseEntity<Map<String,String>> agregarLibro(@RequestBody Libro libro) {
        Map<String,String> response = new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("message", "Libro creado con Éxito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el Libro!");
            return ResponseEntity.badRequest().body(response);
        }
    }


    //Editar
    @PutMapping("/libro")
    public ResponseEntity<Map<String,String>> editarLibro(@RequestParam Long id, @RequestBody Libro nuevoLibro) {
        Map<String,String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libro.setIsbn(nuevoLibro.getIsbn());
            libro.setNombre(nuevoLibro.getNombre());
            libro.setSinopsis(nuevoLibro.getSinopsis());
            libro.setAutor(nuevoLibro.getAutor());
            libro.setEditorial(nuevoLibro.getEditorial());
            libro.setDisponibilidad(nuevoLibro.getDisponibilidad());
            libro.setNumeroEstanteria(nuevoLibro.getNumeroEstanteria());
            libro.setCluster(nuevoLibro.getCluster());
            libro.setCategoria(nuevoLibro.getCategoria());
            libroService.guardarLibro(libro);
            response.put("message", "Se editó el Libro Exitosamente!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al editar el Libro!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Eliminar
    @DeleteMapping("/libro")
    public ResponseEntity<Map<String,String>> eliminarLibro(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libroService.eliminarLibro(libro);
            response.put("message", "Se eliminó el libro con éxito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al elminar!");
            return ResponseEntity.badRequest().body(response);
        }
    }


    
}
