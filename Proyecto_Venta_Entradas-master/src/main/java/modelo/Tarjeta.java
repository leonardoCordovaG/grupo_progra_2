package modelo;
import javax.swing.JOptionPane;

public class Tarjeta {
    private long numero;
    private String nombre;
    private String fecha;
    private int CVV;
    private TipoTarjeta tipo;

    public Tarjeta(long numero, String nombre, String fecha, int CVV, TipoTarjeta tipo) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.CVV = CVV;
        this.tipo = tipo;
    }


    // El número y el CVV ya llegan validados (ver Tarjeta.verificarNumero/verificarCVV): se validan
    // sobre el texto ingresado ANTES de convertirlo a long/int, porque esa conversión pierde los
    // ceros a la izquierda (ej. CVV "0834" se guardaría como 834). Acá solo queda validar nombre y fecha.
    public boolean validarTarjeta() {
        try {
            verificarNombre(this.nombre);
            verificarFecha(this.fecha);
            return true;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void verificarNumero(String numeroStr, TipoTarjeta tipo) {
        int longitudEsperada = tipo.getLongitudNumero();
        if (numeroStr == null || numeroStr.trim().length() != longitudEsperada) {
            throw new IllegalArgumentException("El número de tarjeta " + tipo.getNombre() + " debe tener exactamente " + longitudEsperada + " dígitos.");
        }
        try {
            Long.parseLong(numeroStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número de tarjeta contiene caracteres no válidos. Debe ser puramente numérico.");
        }
    }

    public static void verificarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del titular no puede estar vacío.");
        }
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new IllegalArgumentException("El nombre del titular solo debe contener letras y espacios.");
        }
    }

    public static void verificarFecha(String fecha) {
        if (fecha == null || !fecha.matches("^(0[1-9]|1[0-2])/(2[6-9]|[3-9][0-9])$")) {
            throw new IllegalArgumentException("La fecha de vencimiento debe cumplir el formato MM/AA (Ejemplo: 08/29).");
        }
    }

    public static void verificarCVV(String cvvStr, TipoTarjeta tipo) {
        int longitudEsperada = tipo.getLongitudCVV();
        if (cvvStr == null || cvvStr.trim().length() != longitudEsperada) {
            throw new IllegalArgumentException("El código CVV debe tener exactamente " + longitudEsperada + " dígitos.");
        }
        try {
            Integer.parseInt(cvvStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El CVV contiene caracteres no válidos. Debe ser un número de " + longitudEsperada + " dígitos.");
        }
    }

    public long getNumero() { return numero; }
    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public int getCVV() { return CVV; }
    public TipoTarjeta getTipo() { return tipo; }

    public String getNumeroEnmascarado() {
        String n = String.valueOf(numero);
        String ultimos = n.length() >= 4 ? n.substring(n.length() - 4) : n;
        return "**** **** **** " + ultimos;
    }
}