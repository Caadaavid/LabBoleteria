import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class LectorAsientosCSV {
    public List<Asientos> cargarAsientos(String path) {
        String linea;
        List<Asientos> listaAsientos = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(path))){
            lector.readLine();

            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split(";");
                String idEvento = datos[0].trim();
                String idAsiento = datos[1].trim();
                String localidad = datos[2].trim();
                String fila = datos[3].trim();
                String numero = datos[4].trim();
                Double precio = Double.parseDouble(datos[5]);

                Asientos asiento = new Asientos(idEvento, idAsiento, localidad, fila, numero, precio);
                listaAsientos.add(asiento);
            }
            GestionarLogs.registrarLog("INFO", "Carga exitosa de asientos. Total cargados: "
                    + listaAsientos.size());
        } catch (IOException e){
            GestionarLogs.registrarLog("ERROR", "Error al leer el archivo de asientos" + e.getMessage());
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaAsientos;
    }
}
