public class Asientos {
    private String idEvento;
    private String idAsiento;
    private String localidad;
    private String fila;
    private String numero;
    private Double precio;

    public Asientos(String idEvento, String idAsiento, String localidad, String fila, String numero, Double precio) {
        this.idEvento = idEvento;
        this.idAsiento = idAsiento;
        this.localidad = localidad;
        this.fila = fila;
        this.numero = numero;
        this.precio = precio;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public String getIdAsiento() {
        return idAsiento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getFila() {
        return fila;
    }

    public String getNumero() {
        return numero;
    }

    public Double getPrecio() {
        return precio;
    }

    @Override
    public String toString(){
        return "Asiento: [ID Evento: " + idEvento + " ID Asiento: " + idAsiento + " localidad: " + localidad +
                " fila: " + fila + " número: " + numero + " precio: " + precio + "]";
    }
}
