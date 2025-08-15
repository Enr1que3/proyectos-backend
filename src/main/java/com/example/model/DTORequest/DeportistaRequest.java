package com.example.model.DTORequest;



public class DeportistaRequest {
    private Long id;
    private String nombre;
    private String Ap;
    private String Am;
    private String especialidad;
    
    public DeportistaRequest(){}
    
    public DeportistaRequest(Long id,String nombre, String Ap,String Am,String especialidad){
        this.id=id;
        this.nombre=nombre;
        this.Ap=Ap;
        this.Am=Am;
        this.especialidad=especialidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
     public String getAp(){
        return Ap;
    }
     
    public void setAp(String Ap){
        this.Ap = Ap;
    }

    public String getAm() {
        return Am;
    }

    public void setAm(String Am) {
        this.Am = Am;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
    
}
