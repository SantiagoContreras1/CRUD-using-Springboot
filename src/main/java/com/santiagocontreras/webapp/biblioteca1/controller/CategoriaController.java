package com.santiagocontreras.webapp.biblioteca1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.santiagocontreras.webapp.biblioteca1.model.Categoria;
import com.santiagocontreras.webapp.biblioteca1.service.CategoriaService;

@Controller // Inicia el controllador en toda la aplicacion
@RestController // Nos da el acceso a los métodos de http
@RequestMapping(value = "") // Es la url, lleva el nombre del modelo, da acceso a la API. Este es la parte
                            // del EndPoint
public class CategoriaController {

    @Autowired // Es un objeto que se encarga de inyectar los objetos
    CategoriaService categoriaService;

    @GetMapping("/categorias") // Es para el Listar
    public List<Categoria> listarCategoria() {
        return categoriaService.listarCategoria();
    }

    @GetMapping("/categoria") // Se recibe un pparametro llamado id
    public ResponseEntity<Categoria> buscarCategoriaPorId(@RequestParam Long id) {/* Este es el Buscar */
        /* El Id viene en la path */
        try {
            return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/categoria") // Este es el agregar
    public ResponseEntity<Map<String, Boolean>> /* Le da formato a la respuesta */ agregarCategoria(
        @RequestBody Categoria categoria/* Da acceso al body del request */) {
        Map<String, Boolean> response = new HashMap<>();
        try {
            if (categoriaService.guardarCategoria(categoria)) {
                response.put("Se agrego con exito", Boolean.TRUE);
                return ResponseEntity.ok(response);
            } else {
                response.put("No se pudo agregar la categoria ", Boolean.FALSE);
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            System.out.println(e);
            response.put("No se pudo agregar la categoria ", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Editar
    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id,
            @RequestBody Categoria categoriaNueva) {
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria()); // Se sustituye el nuevo valor por el
                                                                               // viejo
            categoriaService.guardarCategoria(categoria);// Se manda la categoria vieja, ya que el Id se manda en el
                                                         // path
            response.put("message", "Se editó la categoria correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "No se pudo editar la categoria");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Eliminar
    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id) { // Se busca la variable que
                                                                                          // viene en la path
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id); // Se busca el objeto con el Buscar, y
                                                                             // luego se elimina
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "Se eliminó la categoria efectivamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo un error al eliminar la categoría");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
