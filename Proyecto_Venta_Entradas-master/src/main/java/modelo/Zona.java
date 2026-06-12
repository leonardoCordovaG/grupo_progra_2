package modelo;

public class Zona {
    private String nombre;
    private int capacidad;
    private int precio;
    private Entrada[] lista_entradas;

    // Constructor conveniente: genera las entradas automáticamente
    public Zona(String nombre, int capacidad, int precio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.lista_entradas = new Entrada[capacidad];
        for (int i = 0; i < capacidad; i++) {
            this.lista_entradas[i] = new Entrada(i + 1, "disponible");
        }
    }

    // Constructor original con array de entradas externo
    public Zona(String nombre, int capacidad, int precio, Entrada[] listaEntradas) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.lista_entradas = listaEntradas;
    }

    private boolean generarEntradas() {
        return false;
    }

    public Entrada[] mostrarEntrada() { return lista_entradas; }

    public Entrada[] venderEntrada(int numero) { return lista_entradas; }

    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public int getPrecio() { return precio; }

    public int getDisponibles() {
        if (lista_entradas == null) return 0;
        int count = 0;
        for (Entrada e : lista_entradas) {
            if (e != null && "disponible".equals(e.getEstado())) {
                count++;
            }
        }
        return count;
    }
}
