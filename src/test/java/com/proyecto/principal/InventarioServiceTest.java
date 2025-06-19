package com.proyecto.principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.any;

//import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.proyecto.principal.Model.Inventario;
import com.proyecto.principal.Model.Entity.InventarioEntity;
import com.proyecto.principal.Repository.InventarioRepository;
import com.proyecto.principal.Service.InventarioService;

public class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    private Inventario inventario;
    private InventarioEntity inventarioEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        inventario = new Inventario(1, 101, 20, "2024-06-01");
        inventarioEntity = new InventarioEntity();
        inventarioEntity.setIdInventario(1);
        inventarioEntity.setIdPerfume(101);
        inventarioEntity.setCantidadDisponible(20);
        inventarioEntity.setFechaActualizacion("2024-06-01");
    }

    @Test
    public void testCrearInventario_nuevo() {
        when(inventarioRepository.existsByidPerfume(inventario.getIdPerfume())).thenReturn(false);
        when(inventarioRepository.save(any(InventarioEntity.class))).thenReturn(inventarioEntity);

        String result = inventarioService.crearInventario(inventario);
        assertEquals("Inventario creado correctamente", result);
    }

    @Test
    public void testCrearInventario_yaExiste() {
        when(inventarioRepository.existsByidPerfume(inventario.getIdPerfume())).thenReturn(true);

        String result = inventarioService.crearInventario(inventario);
        assertEquals("El inventario ya existe", result);
    }

    @Test
    public void testObtenerInventario_encontrado() {
        when(inventarioRepository.findByidPerfume(101)).thenReturn(inventarioEntity);
        Inventario result = inventarioService.obtenerInventario(101);
        assertEquals(20, result.getCantidadDisponible());
    }

    @Test
    public void testObtenerInventario_noEncontrado() {
        when(inventarioRepository.findByidPerfume(999)).thenReturn(null);
        Inventario result = inventarioService.obtenerInventario(999);
        assertNull(result);
    }

    @Test
    public void testEliminarInventario() {
        doNothing().when(inventarioRepository).deleteById(1);
        String result = inventarioService.eliminarInventario(1);
        assertEquals("Inventario eliminado correctamente", result);
    }
}