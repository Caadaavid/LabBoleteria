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

}
