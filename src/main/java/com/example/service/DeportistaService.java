package com.example.service;

import com.example.model.DTORequest.DeportistaRequest;
import com.example.model.DTOResponse.DeportistaResponse;
import java.util.List;


public interface DeportistaService {
    
    List<DeportistaResponse> listarTodos();
    DeportistaResponse guardar(DeportistaRequest deportistaRequest);
    DeportistaResponse actualizar(Long id, DeportistaRequest deportistaRequest);
    String eliminar(Long id);
    
}
