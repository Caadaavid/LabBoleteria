import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GenerarQR {
    public static String generarCodigoQR(Compra compra){
        String rutaArchivo = "src/qr/" + compra.getIdCompra() + ".png";

        String contenidoQR = String.format(
                "TICKET ID: %s | EVENTO: %s | CLIENTE: %s | ASIENTOS: %s | TOTAL: $%.2f",
                compra.getIdCompra(),
                compra.getIdEvento(),
                compra.getNameCliente(),
                String.join(",", compra.getAsientosComprados()),
                compra.getTotal()
        );

        int ancho = 200;
        int alto = 200;

        try{
            Map<EncodeHintType, Object> configuracion = new HashMap<>();
            configuracion.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            configuracion.put(EncodeHintType.MARGIN, 1);
            configuracion.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    contenidoQR,
                    BarcodeFormat.QR_CODE,
                    ancho,
                    alto,
                    configuracion
            );

            Path path = new File(rutaArchivo).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix,"PNG", path);

            GestionarLogs.registrarLog("INFO", "Codigo QR creado exitosamente en " + rutaArchivo);
            return rutaArchivo;
        } catch (Exception e){
            GestionarLogs.registrarLog("ERROR", "Error al generar código QR para: "
            + compra.getIdCompra() + ": " + e.getMessage());
            return null;
        }
    }
}