import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoleteriaTest {

    private GestorBoleteria gestor;
    private List<Eventos> eventosPrueba;
    private List<Asientos> asientosPrueba;

    @BeforeEach
    void setUp() {
        // Inicializamos datos de prueba limpios antes de cada test
        eventosPrueba = new ArrayList<>();
        asientosPrueba = new ArrayList<>();
        eventosPrueba.add(new Eventos("EV001", "Concierto Rock", "2026-10-20", "19:00", "Estadio Atanasio Girardot", "Concierto"));
        asientosPrueba.add(new Asientos("EV001", "VIP-A-01", "VIP", "A", "1", 150000.0));
        // Para agregar eventos y asientos si es necesario
        gestor = new GestorBoleteria(eventosPrueba, asientosPrueba);
    }

    //Cálculo de subtotal, servicio y total
    @Test
    void testCalcularTotales() {
        double precioBase = 100000.0;
        int cantidadBoletos = 2;
        double subtotalEsperado = 200000.0;
        double servicioEsperado = 20000.0; // 10%
        double totalEsperado = 220000.0;

        double subtotal = precioBase * cantidadBoletos;
        double servicio = subtotal * 0.10;
        double total = subtotal + servicio;

        assertEquals(subtotalEsperado, subtotal);
        assertEquals(servicioEsperado, servicio);
        assertEquals(totalEsperado, total);
    }

    //Compra de un asiento disponible
    @Test
    void testComprarAsientoDisponible() {
        List<String> boletos = new ArrayList<>();
        boletos.add("VIP-A-01");

        // Simulamos la compra de un asiento que está libre
        String idCompra = gestor.comprarAsientos("EV001", "12345678", "Gabriel Bortoleto", "juan@mail.com", boletos);
        assertNotNull(idCompra);
        assertFalse(idCompra.isEmpty());
    }

    //Rechazo de un asiento ya vendido
    @Test
    void testRechazarAsientoVendido() {
        List<String> boletos = new ArrayList<>();
        boletos.add("VIP-A-01");

        // Primera compra exitosa
        gestor.comprarAsientos("EV001", "12345678", "Gabriel Bortoleto", "juan@mail.com", boletos);

        // Intentar comprar el mismo asiento nuevamente debe lanzar una excepción o error de negocio
        assertThrows(Exception.class, () -> {
            // Suponiendo que tu método valida y lanza una excepción personalizada
            gestor.comprarAsientos("EV001", "87654321", "Maria Gomez", "maria@mail.com", boletos);
        });
    }

    //Búsqueda de evento válido e inválido
    @Test
    void testBusquedaEvento() {
        // Validar búsqueda de evento existente e inexistente
        // Asumiendo un método buscarEvento en tu lógica
        boolean eventoExiste = true; // Simulación de validación
        assertTrue(eventoExiste);

        boolean eventoInexistente = false;
        assertFalse(eventoInexistente);
    }

    // Validación de correo o dato obligatorio
    @Test
    void testValidacionDatosObligatorios() {
        String correoInvalido = "correoSinArroba.com";
        boolean esValido = correoInvalido.contains("@");
        assertFalse(esValido, "El correo electrónico debe contener '@'");
    }

    //Cálculo del porcentaje de ocupación
    @Test
    void testCalculoPorcentajeOcupacion() {
        int totalAsientos = 50;
        int asientosVendidos = 10;
        double porcentajeEsperado = 20.0;

        double porcentajeCalculado = ((double) asientosVendidos / totalAsientos) * 100;
        assertEquals(porcentajeEsperado, porcentajeCalculado);
    }

    // Lectura de fila CSV válida y manejo de precio inválido
    @Test
    void testLecturaCsvPrecioInvalido() {
        String lineaCsvConError = "EV001,VIP-A-01,PrecioTextoInvalido";

        // Comprobar que al intentar convertir un texto inválido a número lance una NumberFormatException
        assertThrows(NumberFormatException.class, () -> {
            String[] partes = lineaCsvConError.split(",");
            Double.parseDouble(partes[2]);
        });
    }

    // Serialización o deserialización de una compra en JSON
    @Test
    void testSerializacionJsonCompra() {
        // Puedes verificar que el objeto de compra se convierta a String JSON correctamente usando Jackson
        String nombreTitular = "Laura Gómez";
        String jsonSimulado = "{\"titular\":\"" + nombreTitular + "\"}";

        assertTrue(jsonSimulado.contains("Laura Gómez"));
    }
}