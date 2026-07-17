package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Concierto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private Date fecha;
    private ArrayList<Zona> listaZonas = new ArrayList<>();

    public Concierto(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha  = fecha;
    }

    public boolean agregarZona(Zona zona) {
        if (zona != null) { listaZonas.add(zona); return true; }
        return false;
    }

    public boolean agregarZona(String nombre) {
        return agregarZona(new Zona(nombre, 0, 0, new Entrada[0]));
    }

    public boolean eliminarZona(String nombreZona) {
        return listaZonas.removeIf(z -> z.getNombre().equalsIgnoreCase(nombreZona));
    }

    public ArrayList<Zona> getZonas()      { return listaZonas; }
    public ArrayList<Zona> getListaZonas() { return listaZonas; }
    public void setListaZonas(ArrayList<Zona> l) { this.listaZonas = l; }

    public String getNombre()            { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFecha()           { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getFechaFormateada() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }
}
