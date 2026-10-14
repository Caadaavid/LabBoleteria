import java.util.ArrayList;
import java.util.List;

class GestorBoleteria {
    private List<Eventos> listaEventos;
    private List<Asientos> listaAsientos;
    private static int idCompra = 0;

    public GestorBoleteria(List<Eventos> listaEventos, List<Asientos> listaAsientos) {
        this.listaEventos = listaEventos;
        this.listaAsientos = listaAsientos;
    }

    public List<Asientos> filtrarAsientos (List<Asientos> todosLosAsientos, String idEvento){
         List<Asientos> filtrados = new ArrayList<>();

         for (Asientos a : todosLosAsientos){
             if(idEvento.equalsIgnoreCase(a.getIdEvento())){
                 filtrados.add(a);
             }
         }
         return filtrados;
    }

    public String comprarAsientos (String idEvento, String doc, String name, String email, List<String> boletoSelected){
         validarCampos(idEvento, "ID del evento");
         validarCampos(doc, "Documento de identidad");
         validarCampos(name, "Nombre");
         validarCampos(email, "Correo electronico");

         if (boletoSelected == null || boletoSelected.isEmpty()){
             throw new IllegalArgumentException("Debe seleccionar al menos un asiento para la compra");
         }

         boolean eventExist = false;
         for (Eventos e : listaEventos){
             if(idEvento.equalsIgnoreCase(e.getId())){
                 eventExist = true;
                 break;
             }
         }

         if(!eventExist){
             GestionarLogs.registrarLog("ERROR", "El evento con ID: " + idEvento + " no existe");
             throw new IllegalArgumentException("El ID ingresado no existe en el sistema");
         }

         for (String idBuscado : boletoSelected){
             boolean seatFound = false;

             for (Asientos a : listaAsientos){
                if (a.getIdEvento().equalsIgnoreCase(idEvento) && a.getIdAsiento().equalsIgnoreCase(idBuscado)){
                    seatFound = true;

                    if (!a.isDisponible()){
                        GestionarLogs.registrarLog("ERROR", "Intento de comprar un asiento no disponible "
                                + idBuscado);
                        throw new IllegalArgumentException("El asiento " + idBuscado + " ya esta ocupado.");
                    }
                    break;
                }
             }
             if (!seatFound){
                 GestionarLogs.registrarLog("ERROR", "El asiento " + idBuscado
                         + " no pertenece al evento " + idEvento + ".");
                 throw new IllegalArgumentException("El asiento " + idBuscado
                         + " no pertenece al evento " + idEvento + ".");
             }
         }

         double[] costos = calcularCostos(boletoSelected);
         double subtotal = costos[0];
         double servicio = costos[1];
         double total = costos[2];

         actualizarEstadoAsientos(idEvento, boletoSelected);


         idCompra += 10;
         String idGenerado = String.valueOf(idCompra);
         GestionarLogs.registrarLog("COMPRA", "Compra realizada con exito, ID de compra: " + idGenerado);

         Compra compra = new Compra(idGenerado, idEvento, doc, name, email,
                 boletoSelected, subtotal, servicio, total);

         compra.registrarCompra(compra);
         GenerarTicket.crearTicketPDF(compra);

         return String.valueOf(idCompra);
    }

     public void validarCampos (String valor, String nombreCampo){
         if (valor == null || valor.isBlank()){
             String mensajeError = "El campo " + nombreCampo + " es obligatorio.";
             GestionarLogs.registrarLog("ERROR", mensajeError);
             throw new IllegalArgumentException(mensajeError);
         }
     }

     private double[] calcularCostos(List<String> boletoSelected){
        Double subtotal = (double) 0;

        for(String idBuscado : boletoSelected){
            for (Asientos a : listaAsientos){
                if (a.getIdAsiento().equalsIgnoreCase(idBuscado)){
                    subtotal += a.getPrecio();
                    break;
                }
            }
        }
        double servicio = subtotal*0.1; //10% del servicio.
        double total = subtotal + servicio;

        return new double[]{subtotal, servicio, total};
     }

     private void actualizarEstadoAsientos(String idEvento, List<String> boletoSelected){
        for (String idBuscado : boletoSelected){
            for(Asientos a : listaAsientos){
                if (a.getIdEvento().equalsIgnoreCase(idEvento) && a.getIdAsiento().equalsIgnoreCase(idBuscado)){
                    a.setDisponible(false);
                    break;
                }
            }
        }
     }
}
