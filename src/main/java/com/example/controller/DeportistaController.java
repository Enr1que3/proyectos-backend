package com.example.controller;

import com.example.model.DTORequest.DeportistaRequest;
import com.example.model.DTOResponse.DeportistaResponse;
import com.example.service.DeportistaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class DeportistaController {
    
    private final DeportistaService deportistaService;
    
    @Autowired
    public DeportistaController(DeportistaService deportistaService){
        this.deportistaService = deportistaService;
    }
    
    @GetMapping("/listar-todos")
    public ResponseEntity<List<DeportistaResponse>> listarTodos(){
        List<DeportistaResponse> todos = deportistaService.listarTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    
    @PostMapping("/insertar-registros")
    public ResponseEntity<DeportistaResponse> insertarRegistros(@RequestBody DeportistaRequest deportistaRequest){
        DeportistaResponse guardar = deportistaService.guardar(deportistaRequest);
        return new ResponseEntity<>(guardar, HttpStatus.CREATED);
    }
    
    @PutMapping("/actulizar-registro/{id}")
    public ResponseEntity<DeportistaResponse> actualizarRegistro(@PathVariable Long id, @RequestBody DeportistaRequest deportistaRequest){
        DeportistaResponse actualiza = deportistaService.actualizar(id, deportistaRequest);
        return new ResponseEntity<>(actualiza, HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarRegistro(@PathVariable Long id){
        String eliminar = deportistaService.eliminar(id);
        return new ResponseEntity<>(eliminar, HttpStatus.OK);
    }
}
