package com.example.util;


// https://javatechonline.com/generating-dynamic-pdf-report-using-spring-boot/
import com.example.DAO.IDeportista;
import com.example.model.entity.Deportista;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("pdfGenerator")
public class PdfGenerator {
    
    @Value("${PdfDir}")
    private String pdfDir;
    
    @Value("${FileName}")
    private String fileName;
    
    @Value("${FormatFileDateName}")
    private String localDateFormat;
    
    @Value("${table.columnNames}")
    private List<String> encabezado;
    
    @Autowired
    private IDeportista deportista;
    
    private static Font COURIER = new Font(Font.FontFamily.COURIER,20,Font.BOLD);
    private static Font COURIER_SMALL  = new Font(Font.FontFamily.COURIER,16,Font.BOLD);
    private static Font COURIER_SMALL_FOOTER  = new Font(Font.FontFamily.COURIER,12,Font.BOLD);
    
    
    public void genrarDocumento(){
        Document documento = new Document();
        
        try{
            PdfWriter.getInstance(documento, new FileOutputStream(getPdfNameWithDate()));
            documento.open();
            addDocTitle(documento);
            createTable(documento,4);
            addFooter(documento);
            documento.close();
        }catch(FileNotFoundException | DocumentException e){
            e.printStackTrace();
        }
    }
    
    
    private void addDocTitle(Document document) throws DocumentException {
        String fechaDeCreacion = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
        Paragraph p1 = new Paragraph();

        leaveEmptyLine(p1, 1);
        p1.add(new Paragraph(fileName, COURIER));
        p1.setAlignment(Element.ALIGN_CENTER);
        leaveEmptyLine(p1, 1);
        p1.add(new Paragraph("Reporte de deportistas_" + fechaDeCreacion, COURIER_SMALL));

        document.add(p1);

    }
    
    private void createTable(Document documento, int columnas) throws DocumentException {
        Paragraph parrafo = new Paragraph();
        leaveEmptyLine(parrafo, 3);
        documento.add(parrafo);

        PdfPTable tabla = new PdfPTable(columnas);

        for (int i = 0; i < columnas; i++) {
            PdfPCell filas = new PdfPCell(new Phrase(encabezado.get(i)));
            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
            filas.setBackgroundColor(BaseColor.MAGENTA);
            tabla.addCell(filas);

        }

        tabla.setHeaderRows(1);
        getDBData(tabla);
        documento.add(tabla);

    }
    
    private void getDBData(PdfPTable tabla) {
        List<Deportista> listaDeportista = deportista.findAll();

        for (Deportista d : listaDeportista) {

            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            tabla.addCell(d.getNombre());
            tabla.addCell(d.getAp());
            tabla.addCell(d.getAm());
            tabla.addCell(d.getEspecialidad());

            System.out.println(d);
        }
    }
    
    private void addFooter(Document document) throws DocumentException {
        Paragraph p2 = new Paragraph();
        leaveEmptyLine(p2, 3);
        p2.setAlignment(Element.ALIGN_MIDDLE);
        p2.add(new Paragraph(
                "------------------------Fin de " + fileName + "------------------------",
                COURIER_SMALL_FOOTER));

        document.add(p2);
    }
    
    private static void leaveEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    private String getPdfNameWithDate() {
        String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
        return pdfDir + fileName + " " + localDateString + ".pdf";
    }
    
}
