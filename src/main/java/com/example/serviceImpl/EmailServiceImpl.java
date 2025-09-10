package com.example.serviceImpl;




import com.example.DAO.IDeportista;
import com.example.model.DTORequest.EmailDTO;
import com.example.model.entity.Deportista;
import com.example.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;




@Service
public class EmailServiceImpl implements EmailService{
    
    private final JavaMailSender javaMailSender;
    
    private final TemplateEngine engine;
    
    private IDeportista deportista;

    
    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine engine, IDeportista deportista) {
        this.javaMailSender = javaMailSender;
        this.engine = engine;
        this.deportista = deportista;
    }

    
    

    @Override
    public void sendMail(EmailDTO email) throws MessagingException{
        try{
            
           List<Deportista> lista = deportista.findAll();
           List<Deportista> listaOrdenada = lista.stream().sorted(Comparator.comparing(d -> d.getNombre().toUpperCase())).collect(Collectors.toList());

            
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email.getDestinatario());
            helper.setSubject(email.getAsunto());
            helper.setText(email.getMensaje()); // para enviar texto plano

            Context context = new Context();

            //context.setVariable("mensaje", email.getMensaje());
            context.setVariable("lista", listaOrdenada);

            String contenidoHtml = engine.process("email", context);
            

            helper.setText(contenidoHtml, true);
            javaMailSender.send(message);
            
        }catch(Exception e){
            throw  new RuntimeException("Error al enviar el correo:" + e.getMessage(),e);
        }
    }

}
