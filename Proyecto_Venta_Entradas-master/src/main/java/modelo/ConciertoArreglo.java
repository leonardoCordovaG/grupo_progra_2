package modelo;

import java.io.Serializable;
import java.util.Iterator;

public class ConciertoArreglo implements Iterable<Concierto>, Serializable {
    private static final long serialVersionUID = 1L;

    private Concierto[] datos;
    private int total;

    public ConciertoArreglo(int cantidad) {
        this.datos = new Concierto[cantidad];
        this.total = 0;
    }

    public boolean agregar(Concierto concert) {
        if (total >= datos.length) return false;
        datos[total] = concert;
        total = total + 1;
        return true;
    }

    public Concierto obtener(int i) {
        return datos[i];
    }

    public int tamanio() {
        return total;
    }

    @Override
    public Iterator<Concierto> iterator() {
        return new Iterator<Concierto>() {
            int i = 0;
            @Override public boolean hasNext() { return i < total; }
            
            @Override public Concierto next()  { 
                Concierto c = datos[i];
                i = i + 1;
                return c;
            }
        };
    }
}
