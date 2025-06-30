package com.proyecto.principal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.principal.Model.Inventario;
import com.proyecto.principal.Model.Entity.InventarioEntity;
import com.proyecto.principal.Model.dto.InventarioDto;
import com.proyecto.principal.Service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Operation(summary = "Este endpoint permite crear Inventario")
    @PostMapping("/crearInventario")
    public ResponseEntity<String> crearInventario(@RequestBody Inventario inventario) {
        String resultado = inventarioService.crearInventario(inventario);
        if (resultado.equals("Inventario creado correctamente")) {
            return ResponseEntity.status(201).body(resultado); // 201 Created
        } else if (resultado.equals("El inventario ya existe")) {
            return ResponseEntity.status(409).body(resultado); // 409 Conflict
        }
        return ResponseEntity.badRequest().body(resultado); // 400 Bad Request
    }

    @Operation(summary = "Obtener inventario por ID de Perfume")
    @GetMapping("/obtenerInventario/{idPerfume}")
    public ResponseEntity<Inventario> obtenerInventario(@PathVariable int idPerfume) {
        Inventario inventario = inventarioService.obtenerInventario(idPerfume);
        if (inventario != null) {
            return ResponseEntity.ok(inventario); // 200 OK
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }

    @Operation(summary = "Obtener InventarioDTO por ID de Inventario")
    @GetMapping("/obtenerInventarioDto/{idInventario}")
    public ResponseEntity<InventarioDto> obtenerInventarioDto(@PathVariable int idInventario) {
        InventarioDto dto = inventarioService.obtenerInventarioDto(idInventario);
        if (dto != null) {
            return ResponseEntity.ok(dto); // 200 OK
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }

    @Operation(summary = "Listar todos los inventarios")
    @GetMapping("/listarInventario")
    public ResponseEntity<List<InventarioEntity>> listarInventario() {
        return ResponseEntity.ok(inventarioService.listarInventario()); // 200 OK
    }

    @Operation(summary = "Actualizar un inventario")
    @PutMapping("/actualizarInventario")
    public ResponseEntity<String> actualizarInventario(@RequestBody Inventario inventario) {
        String resultado = inventarioService.actualizarInventario(inventario);
        if (resultado.equals("Inventario actualizado correctamente")) {
            return ResponseEntity.ok(resultado); // 200 OK
        } else if (resultado.equals("Inventario no encontrado")) {
            return ResponseEntity.status(404).body(resultado); // 404 Not Found
        }
        return ResponseEntity.badRequest().body(resultado); // 400 Bad Request
    }

    @Operation(summary = "Eliminar un inventario por ID")
    @DeleteMapping("/eliminarInventario/{idInventario}")
    public ResponseEntity<String> eliminarInventario(@PathVariable int idInventario) {
        String resultado = inventarioService.eliminarInventario(idInventario);
        if (resultado.equals("Inventario eliminado correctamente")) {
            return ResponseEntity.ok(resultado); // 200 OK 
        }
        return ResponseEntity.status(404).body(resultado); // Error
    }

    @Operation(summary = "Obtener información del perfume desde inventario (conexión entre microservicios)")
    @GetMapping("/perfumeDesdeInventario/{idPerfume}")
    public ResponseEntity<String> getPerfumeDesdeInventario(@PathVariable int idPerfume) {
        String perfume = inventarioService.perfumeDesdeInventario(idPerfume);
        if (perfume != null) {
            return ResponseEntity.ok(perfume); // 200 OK
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}