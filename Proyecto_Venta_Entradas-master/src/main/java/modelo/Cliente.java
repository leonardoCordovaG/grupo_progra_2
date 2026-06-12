package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private int puntos;
    private List<Tarjeta> tarjetas;
    private List<Venta> ventas;

    private static List<Cliente> clientesRegistrados = new ArrayList<>();

    public Cliente(int puntos, String nombres, String apellidos, String dni, String contrasena) {
        super(nombres, apellidos, dni, contrasena);
        this.puntos = puntos;
        this.tarjetas = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public static Cliente ingresar(String usuarioDni, String clave) {
        for (Cliente c : clientesRegistrados) {
            if (c.getDni().equals(usuarioDni) && c.getContrasena().equals(clave)) {
                return c;
            }
        }
        return null;
    }

    public static boolean registrarCliente(Cliente nuevoCliente) {
        for (Cliente c : clientesRegistrados) {
            if (c.getDni().equals(nuevoCliente.getDni())) {
                return false;
            }
        }
        clientesRegistrados.add(nuevoCliente);
        return true;
    }

    public int getPuntos() { return puntos; }
    public void setPuntos(int puntos) { this.puntos = puntos; }

    public List<Tarjeta> getTarjetas() { return tarjetas; }

    public boolean agregarTarjeta(Tarjeta t) {
        return tarjetas.add(t);
    }

    public boolean quitarTarjeta(Tarjeta t) {
        return tarjetas.remove(t);
    }

    public List<Venta> getVentas() { return ventas; }

    public void agregarVenta(Venta v) {
        ventas.add(v);
    }
}
