package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Concierto {
    private String nombre;
    private Date fecha;
    private ArrayList<Zona> listaZonas = new ArrayList<>();

    // Catálogo estático compartido por toda la aplicación
    private static ArrayList<Concierto> catalogo = new ArrayList<>();

    public Concierto(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public boolean agregarZona(Zona nuevaZona) {
        if (nuevaZona != null) {
            listaZonas.add(nuevaZona);
            return true;
        }
        return false;
    }

    public boolean agregarZona(String nombre) {
        return agregarZona(new Zona(nombre, 0, 0, new Entrada[0]));
    }

    public boolean eliminarZona(String nombreZona) {
        return listaZonas.removeIf(z -> z.getNombre().equalsIgnoreCase(nombreZona));
    }

    // getZonas() es el alias que usan las vistas; delega en getListaZonas()
    public ArrayList<Zona> getZonas() {
        return listaZonas;
    }

    public ArrayList<Zona> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(ArrayList<Zona> listaZonas) {
        this.listaZonas = listaZonas;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getFechaFormateada() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    // --- Catálogo estático ---
    public static ArrayList<Concierto> getCatalogo() {
        return catalogo;
    }

    public static void agregarAlCatalogo(Concierto c) {
        catalogo.add(c);
    }
}
