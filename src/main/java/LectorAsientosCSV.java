import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class LectorAsientosCSV {
    public static void main(String[] args){
        String path = "src/data/asientos.csv";
        String linea;
        List<Asientos> listaAsientos = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(path))){
            lector.readLine();

            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split(";");
                String idEvento = datos[1].trim();
                String idAsiento = datos[2].trim();
                String localidad = datos[3].trim();
                String fila = datos[4].trim();
                String numero = datos[5].trim();
                Double precio = Double.parseDouble(datos[6]);

            }
        } catch (IOException e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
