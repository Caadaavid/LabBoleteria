import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GestionarLogs {
    private static final String path = "src/Logs/auditoria.txt";
    private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void registrarLog(String tipo, String mensaje){
        String fechaHora = LocalDateTime.now().format(formatoFecha);

        try(PrintWriter writer = new PrintWriter(new FileWriter(path, true))){
            writer.printf("[%s][%s]%s%n", fechaHora, tipo.toUpperCase(), mensaje);
        }catch (IOException e){
            System.out.println("Error al escribir en auditoria.txt" + e.getMessage());
        }
    }
}
