import java.io.*;
import java.util.List;
import java.util.ArrayList;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

public class Compra {
    private String idCompra;
    private String idEvento;
    private String documentoCliente;
    private String nameCliente;
    private String correoCliente;
    private List<String> asientosComprados;
    private Double subtotal;
    private Double cargoServicio;
    private Double total;

    public Compra(int idCompra, String idEvento, String doc, String name, String email, List<String> boletoSelected, double subtotal, double servicio, double total){
    }

    public Compra(String idCompra, String idEvento, String documentoCliente,
                  String correoCliente, List<String> asientosComprados,
                  Double subtotal, Double cargoServicio, Double total) {
        this.idCompra = idCompra;
        this.idEvento = idEvento;
        this.documentoCliente = documentoCliente;
        this.correoCliente = correoCliente;
        this.asientosComprados = asientosComprados;
        this.subtotal = subtotal;
        this.cargoServicio = cargoServicio;
        this.total = total;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public String getNameCliente() {
        return nameCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public List<String> getAsientosComprados() {
        return asientosComprados;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getCargoServicio() {
        return cargoServicio;
    }

    public Double getTotal() {
        return total;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public void setNameCliente(String nameCliente) {
        this.nameCliente = nameCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public void setAsientosComprados(List<String> asientosComprados) {
        this.asientosComprados = asientosComprados;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void setCargoServicio(Double cargoServicio) {
        this.cargoServicio = cargoServicio;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void registrarCompra(Compra nuevaCompra){
        String path = "src/data/compras.json";
        File archivo = new File(path);

        ObjectMapper mapper = JsonMapper.builder()
        .enable(SerializationFeature.INDENT_OUTPUT).build();

        List<Compra> listaCompras = new ArrayList<>();
        if (archivo.exists() && archivo.length() > 0){
            try {
                listaCompras = mapper.readValue(archivo, new TypeReference<List<Compra>>() {});
            } catch (Exception e){
                GestionarLogs.registrarLog("ERROR", "error al leer compras.json" + e.getMessage());
            }
        }
        listaCompras.add(nuevaCompra);

        try {
            mapper.writeValue(archivo, listaCompras);
            GestionarLogs.registrarLog("INFO", "Compra " + nuevaCompra.getIdCompra()
                    + "registrada correctamente en compras.json");
        } catch (Exception e){
            GestionarLogs.registrarLog("ERROR", "Error al escribir en compras.json"
                    + e.getMessage());
        }
    }
}
