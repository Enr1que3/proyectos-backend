package com.example.serviceImpl;

import com.example.DAO.IDeportista;
import com.example.model.DTORequest.UsuarioRequest;
import com.example.model.DTOResponse.DeportistaResponse;
import com.example.model.entity.Deportista;
import com.example.service.UsuarioService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private IDeportista iDeportista;
    
    @Autowired
    public UsuarioServiceImpl(IDeportista iDeportista){
        this.iDeportista = iDeportista;
    }
    
    @Override
    public DeportistaResponse sesion(UsuarioRequest login){
        
        List<Deportista> todos = iDeportista.findAll();
        
        if("amdin".equalsIgnoreCase(login.getUser())){
            todos.stream().map(d -> {
                DeportistaResponse respuesta = new DeportistaResponse();
                respuesta.setId(d.getId());
                respuesta.setNombre(d.getNombre());
                respuesta.setAp(d.getAp());
                respuesta.setAm(d.getAm());
                respuesta.setEspecialidad(d.getEspecialidad());
                return respuesta;
                
            }).collect(Collectors.toList());                    
        }
        
        return null;
    }

    public UsuarioServiceImpl() {
    }
    
}
