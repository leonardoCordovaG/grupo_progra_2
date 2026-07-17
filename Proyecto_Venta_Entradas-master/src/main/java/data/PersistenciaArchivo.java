package data;

import modelo.ClienteArreglo;
import modelo.ConciertoArreglo;
import modelo.VentaArreglo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// Persiste clientes, conciertos (con sus zonas/entradas) y ventas en un único archivo binario,
// serializando directamente los Arreglo de Sistema. Un solo stream preserva las referencias
// compartidas entre objetos (ej. Venta.clienteAsociado apuntando al mismo Cliente que está en
// Sistema.clientes) — si se repartiera en varios archivos, cada uno se leería por separado y esas
// referencias se duplicarían al cargar.
public class PersistenciaArchivo {
    private static final String ARCHIVO = "sistema.dat";

    public static void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(Sistema.clientes);
            oos.writeObject(Sistema.conciertos);
            oos.writeObject(Sistema.ventas);
        } catch (IOException e) {
            System.err.println("No se pudo guardar el archivo de datos: " + e.getMessage());
        }
    }

    // No hace nada si el archivo todavía no existe (primera ejecución): en ese caso queda
    // en pie lo que Sistema.inicializar() y Main ya hayan sembrado.
    public static void cargar() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Sistema.clientes   = (ClienteArreglo) ois.readObject();
            Sistema.conciertos = (ConciertoArreglo) ois.readObject();
            Sistema.ventas     = (VentaArreglo) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudo cargar el archivo de datos: " + e.getMessage());
        }
    }
}
