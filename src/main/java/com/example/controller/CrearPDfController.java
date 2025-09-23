package com.example.controller;

import com.example.service.CrearPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CrearPDfController {
    
    
    private final CrearPdfService crearPdfService;
    
    public CrearPDfController(CrearPdfService crearPdfService){
        this.crearPdfService = crearPdfService;
    }
    

    @GetMapping("/generar-documento")
    public ResponseEntity<String> crearDocumento(){
        crearPdfService.crearPdf();
        return new ResponseEntity<>("PDF CREADO", HttpStatus.OK);
    }
    
}
