package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private int puntos;
    private List<Tarjeta> tarjetas;
    private List<Venta> ventas;

    public Cliente(int puntos, String nombres, String apellidos, String dni, String contrasena) {
        super(nombres, apellidos, dni, contrasena);
        this.puntos = puntos;
        this.tarjetas = new ArrayList<>();
        this.ventas   = new ArrayList<>();
    }

    public int getPuntos()            { return puntos; }
    public void setPuntos(int puntos) { this.puntos = puntos; }

    public List<Tarjeta> getTarjetas() { return tarjetas; }

    public boolean agregarTarjeta(Tarjeta t) { return tarjetas.add(t); }
    public boolean quitarTarjeta(Tarjeta t)  { return tarjetas.remove(t); }

    public List<Venta> getVentas() { return ventas; }
    public void agregarVenta(Venta v) { ventas.add(v); }

    // DIAGNÓSTICO — borrar después de confirmar que el login funciona
    public static int getConteoClientes() {
        return 0; // ya no aplica — usar Sistema.clientes.tamanio()
    }

    public static String getResumenClientes() {
        return ""; // ya no aplica — usar Sistema.clientes
    }
}
