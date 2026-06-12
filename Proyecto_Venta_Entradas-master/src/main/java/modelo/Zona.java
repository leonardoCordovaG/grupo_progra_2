package modelo;

public class Zona {
    private String nombre;
    private int capacidad;
    private int precio;
    private Entrada[] lista_entradas;

    // Constructor conveniente: genera entradas "disponible" automáticamente
    public Zona(String nombre, int capacidad, int precio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.lista_entradas = new Entrada[capacidad];
        for (int i = 0; i < capacidad; i++) {
            this.lista_entradas[i] = new Entrada(i + 1, "disponible");
        }
    }

    // Constructor original con array externo
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

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }

    public Entrada[] getLista_entradas() { return lista_entradas; }
    public void setLista_entradas(Entrada[] lista_entradas) { this.lista_entradas = lista_entradas; }
}
