package com.example.serviceImpl;

import com.example.DAO.IDeportista;
import com.example.model.entity.Deportista;
import com.example.service.CrearWordService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CrearWordServiceImpl implements CrearWordService {

    @Value("${FileName}")
    private String fileName;

    //@Value("${table.columnNames}")
    private List<String> encabezados = Arrays.asList("Nombre", "Apellido Paterno", "Apellido Materno", "Especialiad");

    private final IDeportista deportistas;

    public CrearWordServiceImpl(IDeportista deportistas) {
        this.deportistas = deportistas;
    }

    @Override
    public void generarDocumentoWord() {
        obtenerDatosDB(fileName);
    }

    private void obtenerDatosDB(String nombreArchivo) {
        // creat an empty document
        XWPFDocument documentoEnBlanco = new XWPFDocument();

        XWPFParagraph titulo = documentoEnBlanco.createParagraph();
        XWPFRun textoEnElWord = titulo.createRun();
        
        
        
        List<Deportista> litadoDeportista = deportistas.findAll();

        int columnas = encabezados.size();
        int filas = litadoDeportista.size() + 1;

        try (FileOutputStream guardar = new FileOutputStream(nombreArchivo+".docx")) {
            XWPFTable tabla = documentoEnBlanco.createTable(filas, columnas);// tabla creada
            XWPFTableRow encabezado = tabla.getRow(0);
            
            textoEnElWord.setBold(true);
            textoEnElWord.setFontSize(18);
            
            textoEnElWord.setText("LISTADO DE DEPORTISTAS");
            
            tabla.setTableAlignment(TableRowAlign.CENTER); // Centrar la tabla horizontalmente
            tabla.setWidth("100%"); // Establecer el ancho de la tabla al 100%
            
                       
            encabezado.getCell(0).setText("Nombre");
            encabezado.getCell(1).setText("Apellido Paterno");
            encabezado.getCell(2).setText("Apellido Materno");
            encabezado.getCell(3).setText("Especialidad");

            for (int i = 0; i < litadoDeportista.size(); i++) {
                XWPFTableRow filaDatos = tabla.getRow(i + 1); // +1 para la fila del encabezado
                Deportista d = litadoDeportista.get(i);
                filaDatos.getCell(0).setText(String.valueOf(d.getNombre()));
                filaDatos.getCell(1).setText(d.getAp());
                filaDatos.getCell(2).setText(String.valueOf(d.getAm()));
                filaDatos.getCell(3).setText(String.valueOf(d.getEspecialidad()));
            }

            documentoEnBlanco.write(guardar);
            guardar.close();
            documentoEnBlanco.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al generar el documento de Word.");
        }

    }

}
