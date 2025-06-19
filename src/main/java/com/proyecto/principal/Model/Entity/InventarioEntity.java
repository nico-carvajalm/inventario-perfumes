package com.proyecto.principal.Model.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class InventarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInventario; 


    @Column(name = "idPerfume")
    private int idPerfume;
    
    private int cantidadDisponible;
    private String fechaActualizacion;
}
