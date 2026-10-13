import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerarTicket {
    public static void crearTicketPDF(Compra compra){
        String path = "src/tickets/" + compra.getIdCompra() + ".pdf";
        String rutaQR = GenerarQR.generarCodigoQR(compra);

        try (PDDocument documento = new PDDocument()){
            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            PDType1Font fuenteTitulo = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDType1Font fuenteTexto = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
            PDType1Font fuenteBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

            try (PDPageContentStream contenido = new PDPageContentStream(documento, pagina)){
                contenido.beginText();
                contenido.setFont(fuenteTitulo, 18);
                contenido.newLineAtOffset(50, 750);
                contenido.showText("TICKET DE COMPRA - BOLETERIA");
                contenido.endText();

                contenido.beginText();
                contenido.setFont(fuenteTexto, 12);
                contenido.newLineAtOffset(50, 735);
                contenido.showText("======================================================");
                contenido.endText();

                int y = 700;
                int saltoLinea = 20;

                escribirLinea(contenido, fuenteBold, fuenteTexto, "ID Compra: ", compra.getIdCompra(), 50, y);
                y -= saltoLinea;

                escribirLinea(contenido, fuenteBold, fuenteTexto, "ID Evento: ", compra.getIdEvento(), 50, y);
                y -= saltoLinea;

                escribirLinea(contenido, fuenteBold, fuenteTexto, "Cliente: ", compra.getNameCliente(), 50, y);
                y -= saltoLinea;

                escribirLinea(contenido, fuenteBold, fuenteTexto, "Documento: ", compra.getDocumentoCliente(), 50, y);
                y -= saltoLinea;

                escribirLinea(contenido, fuenteBold, fuenteTexto, "Correo: ", compra.getCorreoCliente(), 50, y);
                y -= saltoLinea;

                String fechaEmision = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                escribirLinea(contenido, fuenteBold, fuenteTexto, "Fecha de Emisión: ", fechaEmision, 50, y);
                y -= (saltoLinea + 10);

                escribirLinea(contenido, fuenteBold, fuenteTexto, "Asientos: ", String.join(", ", compra.getAsientosComprados()), 50, y);
                y -= (saltoLinea + 15);

                contenido.beginText();
                contenido.setFont(fuenteTexto, 12);
                contenido.newLineAtOffset(50, y);
                contenido.showText("-------------------------------------------------");
                contenido.endText();
                y -= saltoLinea;

                escribirLinea(contenido, fuenteBold, fuenteTexto, "Subtotal: ", String.format("$%.2f", compra.getSubtotal()), 50, y);
                y -= saltoLinea;

                escribirLinea(contenido, fuenteBold, fuenteTexto, "Cargo por Servicio (10%): ", String.format("$%.2f", compra.getCargoServicio()), 50, y);
                y -= saltoLinea;

                escribirLinea(contenido, fuenteBold, fuenteTexto, "Total Pagado: ", String.format("$%.2f", compra.getTotal()), 50, y);
                y -= saltoLinea;

                if(rutaQR != null){
                    PDImageXObject imagenQR = PDImageXObject.createFromFile(rutaQR, documento);

                    contenido.drawImage(imagenQR, 50, y - 160, 150, 150);
                }
            }
            documento.save(path);
            GestionarLogs.registrarLog("INFO", "PDF generado exitosamente en " + path);
        } catch (Exception e){
            GestionarLogs.registrarLog("ERROR", "Error al generar el PDF para "
            + compra.getIdCompra() + ": " + e.getMessage());
        }
    }
    private static void escribirLinea(PDPageContentStream stream, PDType1Font fBold, PDType1Font fTexto,
                                      String etiqueta, String valor, float x, float y) throws Exception {
        stream.beginText();
        stream.setFont(fBold, 12);
        stream.newLineAtOffset(x, y);
        stream.showText(etiqueta);
        stream.setFont(fTexto, 12);
        stream.showText(valor != null ? valor : "");
        stream.endText();
    }
}