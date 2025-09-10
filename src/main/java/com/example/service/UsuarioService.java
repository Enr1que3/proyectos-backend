package com.example.service;

import com.example.model.DTORequest.UsuarioRequest;
import com.example.model.DTOResponse.DeportistaResponse;


public interface UsuarioService {
    DeportistaResponse sesion(UsuarioRequest login);
}
