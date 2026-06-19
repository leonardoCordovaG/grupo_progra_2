package modelo;

import java.util.Iterator;

public class VentaArreglo implements Iterable<Venta> {

    private Venta[] datos;
    private int total;

    public VentaArreglo(int capacidad) {
        this.datos = new Venta[capacidad];
        this.total = 0;
    }

    public boolean agregar(Venta v) {
        if (total >= datos.length) return false;
        datos[total++] = v;
        return true;
    }

    public Venta obtener(int i) {
        return datos[i];
    }

    public int tamanio() {
        return total;
    }

    public boolean eliminar(int index) {
        if (index < 0 || index >= total) return false;
        for (int i = index; i < total - 1; i++) {
            datos[i] = datos[i + 1];
        }
        datos[--total] = null;
        return true;
    }

    @Override
    public Iterator<Venta> iterator() {
        return new Iterator<Venta>() {
            int i = 0;
            @Override public boolean hasNext() { return i < total; }
            @Override public Venta next()      { return datos[i++]; }
        };
    }
}
