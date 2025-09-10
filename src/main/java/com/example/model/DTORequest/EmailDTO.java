package com.example.model.DTORequest;


import java.util.List;
import com.example.model.DTORequest.DeportistaRequest;


public class EmailDTO {
    
    
    private String destinatario;
    private String asunto;
    private String mensaje;
    

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
