package com.proyecto.principal.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Perfume {
    private int idPerfume;
    private String nombre;
    private String marca;
    private int precio;
}