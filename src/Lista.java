import java.util.ArrayList;
import java.util.List;

/**
 * Clase Lista
 * Administra los paquetes mediante ArrayList de
 * objetos Paqueteria. Implementa gestión de paquetes, control de estado,
 * operaciones recursivas, algoritmos de ordenamiento (burbuja e inserción)
 * y algoritmos de búsqueda (binaria y por criterios combinados).
 */
public class Lista {

    private ArrayList<Paqueteria> serviEntrega;

    public Lista() {
        this.serviEntrega = new ArrayList<>();
    }

    public ArrayList<Paqueteria> getServiEntrega() {
        return serviEntrega;
    }

    // A. GESTIÓN DE PAQUETES
    // Registra un paquete nuevo. Verifica que no exista un tracking duplicado; si existe, lanza una excepción.

    public void adicionarElementos(Paqueteria p) throws Exception {
        for (Paqueteria aux : serviEntrega) {
            if (aux.getTracking() == p.getTracking()) {
                throw new Exception("El número de tracking " + p.getTracking()
                        + " ya se encuentra registrado.");
            }
        }
        serviEntrega.add(p);
    }

    //Devuelve una cadena con todos los paquetes registrados
    public String listarPaquetes() {
        StringBuilder sb = new StringBuilder();
        for (Paqueteria p : serviEntrega) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    //Busca un paquete por su número de tracking. Lanza excepción si no se encuentra.
    public Paqueteria buscarPaquete(int tracking) throws Exception {
        for (Paqueteria p : serviEntrega) {
            if (p.getTracking() == tracking) {
                return p;
            }
        }
        throw new Exception("No existe un paquete con tracking " + tracking);
    }

    //Modifica peso, ciudades y cédula de un paquete existente
    public void editar(int tracking, double nuevoPeso, String nuevaCiudadRecepcion,
                       String nuevaCiudadEntrega, String nuevaCedula) throws Exception {
        Paqueteria p = buscarPaquete(tracking);
        p.setPeso(nuevoPeso);
        p.setCiudadRecepcion(nuevaCiudadRecepcion);
        p.setCiudadEntrega(nuevaCiudadEntrega);
        p.setCedulaReceptor(nuevaCedula);
    }

    // B. CONTROL DEL ESTADO DE LOS PAQUETES

    //Avanza el estado del paquete según la secuencia definida. Si el paquete ya está "Recibido", indica que el proceso
    // ha finalizado y no modifica el estado.
    // Si el tracking no existe, lanza una excepción.

    public String estadoPaquete(int tracking) throws Exception {
        Paqueteria p = buscarPaquete(tracking);
        String actual = p.getEstado();

        switch (actual) {
            case "Receptado":
                p.setEstado("Enviado");
                return "Enviado";
            case "Enviado":
                p.setEstado("Recibido");
                return "Recibido";
            case "Recibido":
                // El paquete ya fue recibido, no cambia de estado
                return "Finalizado";
            default:
                throw new IllegalArgumentException(
                        "Estado desconocido para el paquete: " + actual);
        }
    }

    // C. OPERACIONES MEDIANTE RECURSIVIDAD
    //Calcula el número total de paquetes registrados mediante recursividad.
     //Caso base: índice fuera de la lista -> retorna 0.
     //Caso recursivo: 1 + llamada al siguiente índice.

    public int sumarTotalPaquetes() {
        return sumarTotalPaquetes(0);
    }

    private int sumarTotalPaquetes(int indice) {
        if (indice >= serviEntrega.size()) {         // caso base
            return 0;
        }
        return 1 + sumarTotalPaquetes(indice + 1);   // caso recursivo
    }

    //Calcula la suma de los pesos de todos los paquetes mediante recursividad
    public double sumarTotalPeso() {
        return sumarTotalPeso(0);
    }

    private double sumarTotalPeso(int indice) {
        if (indice >= serviEntrega.size()) {         // caso base
            return 0.0;
        }
        return serviEntrega.get(indice).getPeso()
                + sumarTotalPeso(indice + 1);        // caso recursivo
    }

    //Calcula el peso total de los paquetes recibidos en una ciudad
    public double sumarTotalPesoCiudad(String ciudad) {
        return sumarTotalPesoCiudad(ciudad, 0);
    }

    private double sumarTotalPesoCiudad(String ciudad, int indice) {
        if (indice >= serviEntrega.size()) {         // caso base
            return 0.0;
        }
        Paqueteria p = serviEntrega.get(indice);
        double pesoActual = p.getCiudadRecepcion().equalsIgnoreCase(ciudad)
                ? p.getPeso()
                : 0.0;
        return pesoActual + sumarTotalPesoCiudad(ciudad, indice + 1); // recursivo
    }

    //Determina cuántos paquetes se encuentran en un estado determinado
    public int sumarTotalPaquetesEstado(String estado) {
        return sumarTotalPaquetesEstado(estado, 0);
    }

    private int sumarTotalPaquetesEstado(String estado, int indice) {
        if (indice >= serviEntrega.size()) {         // caso base
            return 0;
        }
        int aporte = serviEntrega.get(indice).getEstado().equalsIgnoreCase(estado)
                ? 1 : 0;
        return aporte + sumarTotalPaquetesEstado(estado, indice + 1); // recursivo
    }

    // D. ALGORITMOS DE ORDENAMIENTO
    // Ordenamiento por burbuja ascendente, según número de tracking.
    public List<Paqueteria> ordenarPaquetesPorTracking() {
        List<Paqueteria> copia = new ArrayList<>(serviEntrega);
        int n = copia.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (copia.get(j).getTracking() > copia.get(j + 1).getTracking()) {
                    Paqueteria temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        return copia;
    }

    //Ordenamiento por inserción ascendente, según peso.
    public List<Paqueteria> ordenarPaquetesPorPeso() {
        List<Paqueteria> copia = new ArrayList<>(serviEntrega);
        for (int i = 1; i < copia.size(); i++) {
            Paqueteria clave = copia.get(i);
            int j = i - 1;
            while (j >= 0 && copia.get(j).getPeso() > clave.getPeso()) {
                copia.set(j + 1, copia.get(j));
                j--;
            }
            copia.set(j + 1, clave);
        }
        return copia;
    }

    // E. ALGORITMOS DE BÚSQUEDA
    // Búsqueda binaria sobre una lista previamente ordenada por tracking.
    public int busquedaBinaria(List<Paqueteria> listaOrdenada, int tracking) {
        int inicio = 0;
        int fin = listaOrdenada.size() - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int trackingMedio = listaOrdenada.get(medio).getTracking();

            if (trackingMedio == tracking) {
                return medio;
            } else if (trackingMedio < tracking) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1;
    }

    //Búsqueda por criterios combinados: cédula del receptor y estado.
     //Devuelve una lista con los que cumplan ambos criterios.
    public List<Paqueteria> buscarPaquetesPorCedulaYEstado(String cedula, String estado) {
        List<Paqueteria> resultado = new ArrayList<>();
        for (Paqueteria p : serviEntrega) {
            if (p.getCedulaReceptor().equals(cedula)
                    && p.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}