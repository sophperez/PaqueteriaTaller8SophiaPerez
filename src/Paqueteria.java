/**
 * Clase Paqueteria
 * Representa la información de cada paquete registrado en el sistema.
 */
public class Paqueteria {
    private int tracking;
    private double peso;
    private String ciudadRecepcion;
    private String ciudadEntrega;
    private String cedulaReceptor;
    private String estado;

    // Constructor que inicializa los datos del paquete y asigna
    // automáticamente el estado inicial "Receptado".
    public Paqueteria(int tracking, double peso, String ciudadRecepcion,
                      String ciudadEntrega, String cedulaReceptor) {
        this.tracking = tracking;
        this.peso = peso;
        this.ciudadRecepcion = ciudadRecepcion;
        this.ciudadEntrega = ciudadEntrega;
        this.cedulaReceptor = cedulaReceptor;
        this.estado = "Receptado";
    }

    // GET
    public int getTracking() {
        return tracking;
    }

    public double getPeso() {
        return peso;
    }

    public String getCiudadRecepcion() {
        return ciudadRecepcion;
    }

    public String getCiudadEntrega() {
        return ciudadEntrega;
    }

    public String getCedulaReceptor() {
        return cedulaReceptor;
    }

    public String getEstado() {
        return estado;
    }

    // SET
    public void setTracking(int tracking) {
        this.tracking = tracking;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setCiudadRecepcion(String ciudadRecepcion) {
        this.ciudadRecepcion = ciudadRecepcion;
    }

    public void setCiudadEntrega(String ciudadEntrega) {
        this.ciudadEntrega = ciudadEntrega;
    }

    public void setCedulaReceptor(String cedulaReceptor) {
        this.cedulaReceptor = cedulaReceptor;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tracking: " + tracking +
                " | Peso: " + peso + " kg" +
                " | Recepción: " + ciudadRecepcion +
                " | Entrega: " + ciudadEntrega +
                " | Cédula: " + cedulaReceptor +
                " | Estado: " + estado;
    }
}
