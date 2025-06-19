package com.proyecto.principal.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Inventario {
    private int idInventario;
    private int idPerfume;
    private int cantidadDisponible;
    private String fechaActualizacion;
      
}
