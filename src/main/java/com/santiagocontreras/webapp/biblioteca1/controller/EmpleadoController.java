package com.santiagocontreras.webapp.biblioteca1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiagocontreras.webapp.biblioteca1.model.Empleado;
import com.santiagocontreras.webapp.biblioteca1.service.EmpleadoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RestController
@RequestMapping(value = "")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    //Listar
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        try {
            return ResponseEntity.ok(empleadoService.listarEmpleados());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Buscar
    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpleado(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(empleadoService.buscarEmpleado(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/empleado")
    public ResponseEntity<Map<String,String>> agregarEmpleado(@RequestBody Empleado empleado) {
        Map<String,String> response = new HashMap<>();

        try {
            if (empleadoService.guardarEmpleado(empleado)) {
                response.put("message", "Se agrego el Empleado con Éxito!");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "El DPI está duplicado, no se pudo crear el empleado");
                return ResponseEntity.badRequest().body(response);
            }
            
            
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al agregar el Empleado");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/empleado")
    public ResponseEntity<Map<String,String>> editarEmpleado(@RequestParam Long id, @RequestBody Empleado empleadoNew) {
        Map<String,String> response = new HashMap<>();
        
        try {
            Empleado empleado = empleadoService.buscarEmpleado(id);
            empleado.setNombre(empleadoNew.getNombre());
            empleado.setApellido(empleadoNew.getApellido());
            empleado.setTelefono(empleadoNew.getTelefono());
            empleado.setDireccion(empleadoNew.getDireccion());
            empleado.setDpi(empleadoNew.getDpi());
            
            if (empleadoService.guardarEmpleado(empleado)) {
                response.put("message", "Se editó el Empleado con éxito");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "Error al editar al empleado! :[");
                return ResponseEntity.badRequest().body(null);
            }
           

        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al editar al empleado! :[");
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String,String>> eliminarEmpleado(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();

        try {
            Empleado empleado = empleadoService.buscarEmpleado(id);
            empleadoService.eliminarEmpleado(empleado);
            response.put("message", "Se eliminó el empleado exitosamente!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "No se pudo eliminar al Emmpeado");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
    
    
}
