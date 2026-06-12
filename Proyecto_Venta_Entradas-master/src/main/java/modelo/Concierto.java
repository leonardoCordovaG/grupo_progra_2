package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Concierto {
    private String nombre;
    private Date fecha;
    private List<Zona> zonas;

    private static List<Concierto> catalogo = new ArrayList<>();

    public Concierto(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.zonas = new ArrayList<>();
    }

    // Overload que acepta un objeto Zona (para uso desde el catálogo y admin)
    public boolean agregarZona(Zona zona) {
        return zonas.add(zona);
    }

    // Mantiene la firma original del diagrama (stub)
    public boolean agregarZona(String nombre) {
        return false;
    }

    public boolean eliminarZona(String nombre) {
        return zonas.removeIf(z -> z.getNombre().equals(nombre));
    }

    public List<Zona> getZonas() { return zonas; }
    public String getNombre() { return nombre; }
    public Date getFecha() { return fecha; }

    public String getFechaFormateada() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    // Catálogo estático compartido por toda la aplicación
    public static List<Concierto> getCatalogo() { return catalogo; }

    public static void agregarAlCatalogo(Concierto c) {
        catalogo.add(c);
    }
}
