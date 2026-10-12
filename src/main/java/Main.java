import java.util.List;

public class Main {
    public static void main(String[] args){
        LectorAsientosCSV lectorAsientos = new LectorAsientosCSV();
        LectorEventosCSV lectorEventos = new LectorEventosCSV();

        List<Asientos> asientos = lectorAsientos.cargarAsientos("src/data/asientos.csv");
        List<Eventos> eventos = lectorEventos.cargarEventos("src/data/eventos.csv");

        System.out.println("=== ASIENTOS CARGADOS (" + asientos.size() + ") ===");
        for (Asientos a : asientos){
            System.out.println(a);
        }

        System.out.println("=== EVENTOS CARGADOS ===");
        for (Eventos e : eventos){
            System.out.println(e);
        }
    }
}
