import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        LectorAsientosCSV lectorAsientos = new LectorAsientosCSV();
        LectorEventosCSV lectorEventos = new LectorEventosCSV();
        int x = 1;

        List<Asientos> asientos = lectorAsientos.cargarAsientos("src/data/asientos.csv");
        List<Eventos> eventos = lectorEventos.cargarEventos("src/data/eventos.csv");
        GestorBoleteria gestor = new GestorBoleteria(eventos, asientos);

        System.out.println("=== EVENTOS CARGADOS ===");
        for (Eventos e : eventos){
            System.out.println(e);
        }

        System.out.println("");

        while (x != 0){
            System.out.println("=== MENÚ DEL SISTEMA ===");
            System.out.println("1. Mostrar asientos de algún evento");
            System.out.println("2. Comprar asientos");
            System.out.println("0. Salir");
            System.out.println("Seleccionar opción: ");

            try {

            x = Integer.parseInt(scanner.nextLine());

            switch (x){
                case 1:
                    System.out.println("Ingresa ID del evento que quieres filtrar: ");
                    String idFiltrar = scanner.nextLine();
                    List<Asientos> asientosFiltrados = gestor.filtrarAsientos(asientos, idFiltrar);

                    System.out.println("=== ASIENTOS DEL EVENTO " + idFiltrar + " CARGADOS ===");
                    for (Asientos a : asientosFiltrados) {
                        System.out.println(a);
                    }
                break;
                case 2:
                    System.out.println("Ingrede ID del evento: ");
                    String idEvento = scanner.nextLine();

                    System.out.println("Ingrese su número de documento: ");
                    String doc = scanner.nextLine();

                    System.out.println("Ingrese su nombre: ");
                    String name = scanner.nextLine();

                    System.out.println("Ingrese su correo: ");
                    String email = scanner.nextLine();

                    System.out.println("Cantidad de boletos a comprar: ");
                    int cboletos = Integer.parseInt(scanner.nextLine());

                    if (cboletos <= 0){
                        System.out.println("Cantidad solicitada invalida");
                        break;
                    }

                    List<String> boletoSelected = new ArrayList<>();
                    for (int i = 0; i < cboletos; i++){
                        System.out.println("Ingrese el ID del asiento #" + (i+1) + "que desea comprar: ");
                        boletoSelected.add(scanner.nextLine());
                    }
                    // TODO: Envolver la llamada al gestor en un bloque try-catch
                    // para capturar las excepciones personalizadas (RF4) que lance el gestor
                    // y mostrar el mensaje de error al usuario sin romper el ciclo del menú.
                    gestor.comprarAsientos(idEvento, doc, name, email, boletoSelected);
                break;
            }
            } catch (NumberFormatException e){
                System.out.println("Debe ingresar un número valido: " + e.getMessage());
            } catch (IllegalArgumentException e){
                System.out.println("Error en la compra: " + e.getMessage());
            }
        }


    }
}
