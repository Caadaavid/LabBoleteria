import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        LectorAsientosCSV lectorAsientos = new LectorAsientosCSV();
        LectorEventosCSV lectorEventos = new LectorEventosCSV();

        List<Asientos> asientos = lectorAsientos.cargarAsientos("src/data/asientos.csv");
        List<Eventos> eventos = lectorEventos.cargarEventos("src/data/eventos.csv");
        GestorBoleteria gestor = new GestorBoleteria();

        System.out.println("=== ASIENTOS CARGADOS (" + asientos.size() + ") ===");
        for (Asientos a : asientos){
            System.out.println(a);
        }

        System.out.println("=== EVENTOS CARGADOS ===");
        for (Eventos e : eventos){
            System.out.println(e);
        }

        System.out.println("Ingresa ID del evento que quieres filtrar: ");
        String idFiltrar = scanner.nextLine();
        List<Asientos> asientosFiltrados = gestor.filtrarAsientos(asientos, idFiltrar);

        System.out.println("=== ASIENTOS DEL EVENTO " + idFiltrar + " CARGADOS ===");
        for (Asientos a : asientosFiltrados) {
            System.out.println(a);
        }

    }
}
