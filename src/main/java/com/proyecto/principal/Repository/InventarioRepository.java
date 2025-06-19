package com.proyecto.principal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.principal.Model.Entity.InventarioEntity;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer> {

    InventarioEntity findByidPerfume(int idPerfume);
    Boolean existsByidPerfume(int idPerfume);
    void deleteByidPerfume(int idPerfume);

    InventarioEntity findByIdInventario(int idInventario);
    
}
