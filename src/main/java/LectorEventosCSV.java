import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LectorEventosCSV {
    public List<Eventos> cargarEventos(String path) {
        String linea;
        List<Eventos> listaEventos = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(path))){
            lector.readLine();

            while((linea = lector.readLine()) != null){
                String[] datos = linea.split(";");
                String idEvento = datos[0].trim();
                String nombre = datos[1].trim();
                String fecha = datos[2].trim();
                String hora = datos[3].trim();
                String lugar = datos[4].trim();
                String tipoEvento = datos[5].trim();

                Eventos evento = new Eventos(idEvento, nombre, fecha, hora, lugar, tipoEvento);
                listaEventos.add(evento);
            }
        } catch (IOException e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaEventos;
    }
}
