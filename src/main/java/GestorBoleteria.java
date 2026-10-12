import java.util.ArrayList;
import java.util.List;

class GestorBoleteria {

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
         // TODO 1: Validar datos obligatorios (RF4)
         // - Comprobar que doc, name y email no estén vacíos.
         // - Validar el formato del correo electrónico.
         // - Si falla, lanzar una excepción personalizada (ej. DatosInvalidosException) y registrar el error en auditoria.txt.

         // TODO 2: Validar evento y disponibilidad de asientos (RF4)
         // - Verificar que el idEvento exista.
         // - Recorrer boletosSeleccionados y confirmar que ningún asiento esté previamente vendido/ocupado.
         // - Si un asiento ya está ocupado, lanzar excepción y registrar en auditoria.txt.

         // TODO 3: Actualizar estado de los asientos
         // - Cambiar el estado de los asientos seleccionados a "ocupado/vendido".

         // TODO 4: Calcular costos de la compra (RF3)
         // - Sumar el precio individual de cada asiento seleccionado (subtotal).
         // - Calcular el 10% de cobro por servicio.
         // - Calcular el total (subtotal + servicio).

         // TODO 5: Persistencia en JSON (RF5)
         // - Generar un ID único de compra (ej. CMP-001).
         // - Crear el objeto Compra y guardarlo/actualizarlo en data/compras.json utilizando Gson/Jackson.

         // TODO 6: Generación de Ticket y QR (RF6 y RF7)
         // - Llamar al servicio de PDF para crear tickets/<idCompra>.pdf.
         // - Incluir el código QR generado con ZXing conteniendo: ID_COMPRA, TITULAR, EVENTO, ASIENTOS y TOTAL.

         // TODO 7: Auditoría de éxito (RF5)
         // - Escribir un log exitoso en logs/auditoria.txt indicando la compra realizada y el ID asignado.
     }

}
