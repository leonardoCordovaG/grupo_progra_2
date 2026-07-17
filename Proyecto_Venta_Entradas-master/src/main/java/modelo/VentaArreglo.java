package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public boolean eliminar(Venta v) {
        for (int i = 0; i < total; i++) {
            if (datos[i] == v) {
                return eliminar(i);
            }
        }
        return false;
    }

    // Única fuente de verdad para "las ventas de un cliente": Cliente ya no guarda su propia
    // lista de ventas, así se evita que ambas queden desincronizadas (ej. al anular desde el panel de admin).
    public List<Venta> buscarPorCliente(Cliente c) {
        List<Venta> resultado = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            if (datos[i].getClienteAsociado() != null && datos[i].getClienteAsociado().getDni().equals(c.getDni())) {
                resultado.add(datos[i]);
            }
        }
        return resultado;
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
