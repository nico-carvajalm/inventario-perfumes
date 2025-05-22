package com.proyecto.principal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.principal.Model.Inventario;
//import com.proyecto.principal.Model.Perfume;
import com.proyecto.principal.Model.Entity.InventarioEntity;
import com.proyecto.principal.Model.dto.InventarioDto;
import com.proyecto.principal.Service.InventarioService;
//import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @Operation(summary = "Este endpoint permite crear Inventario")
    @PostMapping("/crearInventario")
    // ResponeEntity <-- Responder según acción o resultado
    // 404 <-- No se encuentra el recurso
    // 200 <-- Ok
    public ResponseEntity<String> crearInventario(@RequestBody Inventario inventario){
        return ResponseEntity.ok(inventarioService.crearInventario(inventario));
    }

    @GetMapping("/obtenerInventario/{idPerfume}")
    public ResponseEntity<Inventario> obtenerInventario(@PathVariable int idPerfume){
        Inventario inventario = inventarioService.obtenerInventario(idPerfume);
        if(inventario != null){
            return ResponseEntity.ok(inventario);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtenerInventarioDto/{idInventario}")
    public ResponseEntity<InventarioDto> obtenerInventarioDto(@PathVariable int idInventario){
        if (inventarioService.obtenerInventarioDto(idInventario) != null){
            return ResponseEntity.ok(inventarioService.obtenerInventarioDto(idInventario));
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Listar todos los inventarios")
    @GetMapping("/listarInventario")
    public ResponseEntity<List<InventarioEntity>> listarInventario() {
    return ResponseEntity.ok(inventarioService.listarInventario());
    }   

    @Operation(summary = "Actualizar un inventario")
    @PutMapping("/actualizarInventario")
    public ResponseEntity<String> actualizarInventario(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.actualizarInventario(inventario));
    }

    @Operation(summary = "Eliminar un inventario por ID")
    @DeleteMapping("/eliminarInventario/{idInventario}")
    public ResponseEntity<String> eliminarInventario(@PathVariable int idInventario) {
        return ResponseEntity.ok(inventarioService.eliminarInventario(idInventario));
    }

    @GetMapping("/perfumeDesdeInventario/{idPerfume}")
    public ResponseEntity<String> getPerfumeDesdeInventario(@PathVariable int idPerfume) {
        String perfume = inventarioService.perfumeDesdeInventario(idPerfume);
        if (perfume != null) {
            return ResponseEntity.ok(perfume);
        }
        return ResponseEntity.notFound().build();
    }
}
   
