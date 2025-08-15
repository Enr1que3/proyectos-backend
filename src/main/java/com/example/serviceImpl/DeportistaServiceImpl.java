package com.example.serviceImpl;

import com.example.DAO.IDeportista;
import com.example.model.DTORequest.DeportistaRequest;
import com.example.model.DTOResponse.DeportistaResponse;
import com.example.model.entity.Deportista;
import com.example.service.DeportistaService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class DeportistaServiceImpl implements DeportistaService{
    
    private IDeportista iDeportista;
    
    @Autowired
    public DeportistaServiceImpl(IDeportista iDeportista){
        this.iDeportista = iDeportista;
    }

    @Override
    public List<DeportistaResponse> listarTodos() {
        List<Deportista> deportistas = iDeportista.findAll();
        List<DeportistaResponse> listaADevolver = new ArrayList<>();
        
        deportistas.forEach(d -> {
            DeportistaResponse respuesta = new DeportistaResponse();
            respuesta.setId(d.getId());
            respuesta.setNombre(d.getNombre());
            respuesta.setAp(d.getAp());
            respuesta.setAm(d.getAm());
            respuesta.setEspecialidad(d.getEspecialidad());
            listaADevolver.add(respuesta);
        });
        
        
        return listaADevolver;
    }

    @Override
    public DeportistaResponse guardar(DeportistaRequest deportistaRequest) {
        Deportista deportista = new Deportista();
        deportista.setId(deportistaRequest.getId());
        deportista.setNombre(deportistaRequest.getNombre());
        deportista.setAp(deportistaRequest.getAp());
        deportista.setAm(deportistaRequest.getAm());
        deportista.setEspecialidad(deportistaRequest.getEspecialidad());
        Deportista guardado = iDeportista.save(deportista);
        
        DeportistaResponse respuesta = new DeportistaResponse();
        respuesta.setId(guardado.getId());
        respuesta.setNombre(guardado.getNombre());
        respuesta.setAp(guardado.getAp());
        respuesta.setAm(guardado.getAm());
        respuesta.setEspecialidad(guardado.getEspecialidad());
        
        return respuesta;
        
    }

    @Override
    @Transactional
    public DeportistaResponse actualizar(Long id, DeportistaRequest deportistaRequest) {
        Optional<Deportista> existe = iDeportista.findById(id);
        
        if(existe.isPresent()){
            
            iDeportista.findByUpdate(id, deportistaRequest.getNombre(), deportistaRequest.getAp(), deportistaRequest.getAm(), deportistaRequest.getEspecialidad());
            
            Deportista actual = existe.get();
              
            actual.setNombre(deportistaRequest.getNombre());
            actual.setAp(deportistaRequest.getAp());
            actual.setAm(deportistaRequest.getAm());
            actual.setEspecialidad(deportistaRequest.getEspecialidad());

            DeportistaResponse respuesta = new DeportistaResponse();
            respuesta.setId(actual.getId());
            respuesta.setNombre(actual.getNombre());
            respuesta.setAp(actual.getAp());
            respuesta.setAm(actual.getAm());
            respuesta.setEspecialidad(actual.getEspecialidad());
            
            return respuesta;
        }else {
            return null;
        }

    }

    @Override
    public String eliminar(Long id) {
        iDeportista.deleteById(id);
        
        return "Usuario con id: "+id +" eliminado.";
    }
    
    
}
