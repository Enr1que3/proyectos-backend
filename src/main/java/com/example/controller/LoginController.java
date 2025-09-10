package com.example.controller;

import com.example.model.DTORequest.UsuarioRequest;
import com.example.model.DTOResponse.DeportistaResponse;
import com.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
public class LoginController {
    
    private UsuarioService uService;
    
    @Autowired
    public LoginController(UsuarioService uService){
        this.uService = uService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<DeportistaResponse> login(@RequestBody UsuarioRequest login){
        DeportistaResponse respuesta = uService.sesion(login);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
    
}
