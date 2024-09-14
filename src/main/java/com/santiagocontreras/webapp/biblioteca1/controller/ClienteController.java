package com.santiagocontreras.webapp.biblioteca1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.santiagocontreras.webapp.biblioteca1.model.Cliente;
import com.santiagocontreras.webapp.biblioteca1.service.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RestController
@RequestMapping(value = "")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes") //Listar
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }
    
    //Buscar
    @GetMapping("/cliente")
    public ResponseEntity<Cliente> buscarCliente(@RequestParam Long id){
        try {
            return ResponseEntity.ok(clienteService.buscarCliente(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/cliente")
    public ResponseEntity<Map<String,String>> agregarCliente(@RequestBody Cliente cliente){
        Map<String,String> response = new HashMap<>();
        try {
            clienteService.guardarCliente(cliente);
            response.put("message", "Se ha agregado con Éxito!");
            return ResponseEntity.ok(response); 
        } catch (Exception e) {
            response.put("message", "No se pudo agregar al Cliente :[");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/cliente")
    public ResponseEntity<Map<String,String>> editarCliente(@RequestParam Long id, @RequestBody Cliente nuevoCliente) {
        Map<String,String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarCliente(id);
            cliente.setNombre(nuevoCliente.getNombre());
            cliente.setApellido(nuevoCliente.getApellido());
            cliente.setTelefono(nuevoCliente.getTelefono());
            clienteService.guardarCliente(cliente);
            response.put("message", "Se editó el Cliente con Éxito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "No se pudo editar al Cliente");
            return ResponseEntity.badRequest().body(response);
        }
        
    }

    //Eliminar
    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String,String>> eliminarCliente(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarCliente(id);
            clienteService.eliminarCiente(cliente);
            response.put("message", "Se eliminó el Cliente con Éxito!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "No se pudo eliminar al Cliente.");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
}
