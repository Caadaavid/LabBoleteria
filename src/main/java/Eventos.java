public class Eventos {
    private String id;
    private String nombre;
    private String fecha;
    private String hora;
    private String lugar;
    private String tipoEvento;

    public Eventos(String id, String nombre, String fecha, String hora, String lugar, String tipoEvento) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.tipoEvento = tipoEvento;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getLugar() {
        return lugar;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    @Override
    public String toString(){
        return "Evento [ID: " + id + " Nombre: " + nombre + " fecha: "+ fecha +
               " hora: " + hora + " ubicación: " + lugar + " Tipo de evento: " + tipoEvento + "]";
    }


}
