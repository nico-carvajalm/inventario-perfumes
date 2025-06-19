package com.proyecto.principal.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InventarioDto {
    private int idPerfume;
    private int cantidadDisponible;
}
