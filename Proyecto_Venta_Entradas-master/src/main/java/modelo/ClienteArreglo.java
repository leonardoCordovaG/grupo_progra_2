package modelo;

import java.io.Serializable;
import java.util.Iterator;

public class ClienteArreglo implements Iterable<Cliente>, Serializable {
    private static final long serialVersionUID = 1L;

    private Cliente[] datos;
    private int total;

    public ClienteArreglo(int capacidad) {
        this.datos = new Cliente[capacidad];
        this.total = 0;
    }

    public boolean agregar(Cliente c) {
        if (total >= datos.length) return false;
        for (int i = 0; i < total; i++) {
            if (datos[i].getDni().equals(c.getDni())) return false; // DNI duplicado
        }
        datos[total++] = c;
        return true;
    }

    public Cliente obtener(int i) {
        return datos[i];
    }

    public int tamanio() {
        return total;
    }

    public Cliente buscarPorCredenciales(String dni, String clave) {
        for (int i = 0; i < total; i++) {
            if (datos[i].getDni().equals(dni) && datos[i].getContrasena().equals(clave)) {
                return datos[i];
            }
        }
        return null;
    }

    @Override
    public Iterator<Cliente> iterator() {
        return new Iterator<Cliente>() {
            int i = 0;
            @Override public boolean hasNext() { return i < total; }
            @Override public Cliente next()    { return datos[i++]; }
        };
    }
}
