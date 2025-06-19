package com.proyecto.principal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import com.proyecto.principal.Model.Inventario;
//import com.proyecto.principal.Model.Perfume;
import com.proyecto.principal.Model.Entity.InventarioEntity;
import com.proyecto.principal.Model.dto.InventarioDto;
import com.proyecto.principal.Repository.InventarioRepository;

@Service

public class InventarioService {
    @Autowired
    private InventarioRepository inventariorepository;
    //private RestTemplate restTemplate;

    //public String obtenerPerfumeporId(int idPerfume){
      //  String url = "http://localhost:8080/obtenerUsuario/"+userId;
        //String perfumeData = restTemplate.getForObject(usuarioUrl, String.class);
        //String postUrl = "https://jsonplaceholder.typicode.com/posts?userId="+userId;
        //String postData = restTemplate.getForObject(postUrl, String.class);

        //return "{\"usuario\": "+usuarioData+",\"post\":"+postData+"}";
    //}
    @Autowired
    private RestTemplate restTemplate;

    public String perfumeDesdeInventario(int idPerfume) {
        String url = "http://localhost:8081/obtenerPerfume/" + idPerfume; 
        String data = restTemplate.getForObject(url, String.class);

        return "{\"PERFUME\":" + data + "}";
    }

    public String crearInventario(Inventario inventario){

        try {
            Boolean estado = inventariorepository.existsByidPerfume(inventario.getIdPerfume());
            if (!estado){
                InventarioEntity nuevoInventario = new InventarioEntity();
                //nuevoInventario.setIdInventario(inventario.getIdInventario());
                nuevoInventario.setIdPerfume(inventario.getIdPerfume());
                nuevoInventario.setCantidadDisponible(inventario.getCantidadDisponible());
                nuevoInventario.setFechaActualizacion(inventario.getFechaActualizacion());
                inventariorepository.save(nuevoInventario);
                return "Inventario creado correctamente";
            }
            return "El inventario ya existe";
        } catch (Exception e) {
            e.printStackTrace();
    return "Error al crear inventario: " + e.getMessage();
        }
    }

    public Inventario obtenerInventario(int idPerfume){
        try{
            InventarioEntity inventario = inventariorepository.findByidPerfume(idPerfume);
            if (inventario != null){
                Inventario inv = new Inventario(
                    inventario.getIdInventario(),
                    inventario.getIdPerfume(),
                    inventario.getCantidadDisponible(),
                    inventario.getFechaActualizacion()
                );
                return inv;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public List<InventarioEntity> listarInventario(){
        return inventariorepository.findAll();
    }

    public String actualizarInventario(Inventario inventario){
        try{
            InventarioEntity existente = inventariorepository.findByidPerfume(inventario.getIdPerfume());
            if (existente != null){
                existente.setCantidadDisponible(inventario.getCantidadDisponible());
                existente.setFechaActualizacion(inventario.getFechaActualizacion());
                inventariorepository.save(existente);
                return "Inventario actualizado correctamente";
            }else{
                return "Inventario no encontrado";
            }
        }catch(Exception e){
            return "Error al actualizar inventario";
        }
    }

    public String eliminarInventario(int idInventario){
        try{
            inventariorepository.deleteById(idInventario);
            return "Inventario eliminado correctamente";
        } catch (Exception e){
            return "Error al eliminar inventario";
        }
    }

    public InventarioDto obtenerInventarioDto(int id){
        try{
            InventarioEntity inventario = inventariorepository.findByIdInventario(id);
            InventarioDto nuevoInventario = new InventarioDto(
                inventario.getIdPerfume(),
                inventario.getCantidadDisponible()
            );
            return nuevoInventario;
    }catch(Exception e){
            return null;
        }
    }
}
