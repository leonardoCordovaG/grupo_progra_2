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
    
    public static void verificarTexto(String texto, String tipoCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El " + tipoCampo + " no puede estar vacío.");
        }
        if (!texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new IllegalArgumentException("El " + tipoCampo + " solo debe contener letras y espacios (sin números).");
        }
    }
    
    public static void verificarContrasena(String clave) {
        if (clave == null || clave.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        if (clave.length() < 4) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres.");
        }
    
        if (!clave.matches("^[a-zA-Z0-9@#$%*_\\-\\.]+$")) {
            throw new IllegalArgumentException("La contraseña contiene caracteres no permitidos. Use letras, números o símbolos comunes (@, #, $, %, *, _, -, .).");
        }
}
}
