package com.example.controller;

import com.example.model.DTORequest.EmailDTO;
import com.example.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class EmailController {
    
    private EmailService emailService;
    
    @Autowired

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @PostMapping("/send-mail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO dto) throws MessagingException{
        emailService.sendMail(dto);
        return new ResponseEntity<>("correo enviado exitosamente", HttpStatus.OK);
    }
    
    
}
