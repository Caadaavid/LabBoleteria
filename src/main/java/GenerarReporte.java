import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;

public class GenerarReporte {
    public static void crearGraficoGanancias(Double g1, Double g2, Double g3){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(g1, "Ganancias ($)", "EV001");
        dataset.addValue(g2, "Ganancias ($)", "EV002");
        dataset.addValue(g3, "Ganancias ($)", "EV003");

        JFreeChart chart = ChartFactory.createBarChart(
                "Reporte general de ganancias por evento",
                "Evento",
                "Recaudado ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        String path = "src/reportes/reporte_ganancias.png";

        try {
            ChartUtils.saveChartAsPNG(new File(path), chart, 600, 400);
            GestionarLogs.registrarLog("INFO", "Reporte de ganancias generado exitosamente en " + path);
            System.out.println("Gráfico generado exitosamente en " + path);
        } catch (IOException e){
            GestionarLogs.registrarLog("ERROR", "Error al generar gráfico de ganancias: " + e.getMessage());
            System.out.println("Error al generar la imagen del gráfico: " + e.getMessage());
        }
    }

    public static void crearGraficoBoletos(int b1, int b2, int b3){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(b1, "Boletos vendidos", "EV001");
        dataset.addValue(b2, "Boletos vendidos", "EV002");
        dataset.addValue(b3, "Boletos vendidos", "EV003");

        JFreeChart chart = ChartFactory.createBarChart(
                "Reporte general de boletos vendidos por evento",
                "Evento",
                "Cantidad de boletos",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        String path = "src/reportes/reporte_boletos.png";

        try {
            ChartUtils.saveChartAsPNG(new File(path), chart, 600, 400);
            GestionarLogs.registrarLog("INFO", "Reporte de boletos generado exitosamente en " + path);
            System.out.println("Gráfico de boletos generado exitosamente en " + path);
        } catch (IOException e){
            GestionarLogs.registrarLog("ERROR", "Error al generar gráfico de boletos" + e.getMessage());
            System.out.println("Error al generar gráfico de boletos");
        }
    }

    public static void generarTodosLosReportes(int b1, int b2, int b3, Double g1, Double g2, Double g3){
        crearGraficoGanancias(g1, g2, g3);
        crearGraficoBoletos(b1, b2, b3);
    }

    public static void generarPDFReportes(){
        String pathPDF = "src/reportes/ReporteGeneral.pdf";
        String pathImgGanancias = "src/reportes/reporte_ganancias.png";
        String pathImgBoletos = "src/reportes/reporte_boletos.png";

        File fileGanancias = new File(pathImgGanancias);
        File fileBoletos = new File(pathImgBoletos);

        try(PDDocument documento = new PDDocument()){
            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            PDType1Font fuenteTitulo = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDType1Font fuenteSubtitulo = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

            try (PDPageContentStream contenido = new PDPageContentStream(documento, pagina)){
                contenido.beginText();
                contenido.setFont(fuenteTitulo, 18);
                contenido.newLineAtOffset(50, 750);
                contenido.showText("REPORTE ESTADÍSTICO DE VENTAS");
                contenido.endText();

                PDImageXObject imgGanancias = PDImageXObject.createFromFile(pathImgGanancias, documento);
                PDImageXObject imgBoletos = PDImageXObject.createFromFile(pathImgBoletos, documento);

                float ancho = 450;
                float alto = 270;

                contenido.beginText();
                contenido.setFont(fuenteSubtitulo, 12);
                contenido.newLineAtOffset(50, 720);
                contenido.showText("1. Recaudación Total por Evento ($)");
                contenido.endText();

                contenido.drawImage(imgGanancias, 50, 440, ancho, alto);

                contenido.beginText();
                contenido.setFont(fuenteSubtitulo, 12);
                contenido.newLineAtOffset(50, 410);
                contenido.showText("2. Cantidad de Boletos Vendidos por Evento");
                contenido.endText();

                contenido.drawImage(imgBoletos, 50, 130, ancho, alto);
            }

            documento.save(pathPDF);
            GestionarLogs.registrarLog("INFO", "PDF de reporte general creado con exito en " + pathPDF);
            System.out.println("PDF de reporte general creado con exito en " + pathPDF);
        }catch (Exception e){
            GestionarLogs.registrarLog("ERROR", "Error al generar el PDF de reporte general " + e.getMessage());
            System.out.println("Error al generar el PDF del reporte: " + e.getMessage());
        }
    }
}
