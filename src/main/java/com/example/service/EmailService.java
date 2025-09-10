package com.example.service;

import com.example.model.DTORequest.EmailDTO;
import jakarta.mail.MessagingException;



public interface EmailService {
    void sendMail(EmailDTO email) throws MessagingException;
}
