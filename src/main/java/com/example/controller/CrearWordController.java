package com.example.controller;

import com.example.service.CrearWordService;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CrearWordController {
    
    private final CrearWordService wordService;
    
    public CrearWordController(CrearWordService wordService){
        this.wordService = wordService;
    }
    
    @GetMapping("/generar-word")
    public ResponseEntity<String> generarWord() throws IOException{
        wordService.generarDocumentoWord();
        return new ResponseEntity<>("Word Creado.", HttpStatus.OK);
    }
    
}
