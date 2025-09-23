package com.example.serviceImpl;

import com.example.service.CrearPdfService;
import com.example.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrearPdfServiceImpl implements CrearPdfService{
    
    @Autowired
    private PdfGenerator pdfGenerator;
    
    

    @Override
    public void crearPdf() {
        pdfGenerator.genrarDocumento();
    }

}
