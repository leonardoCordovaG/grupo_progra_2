package modelo;
import javax.swing.JOptionPane;

public class Tarjeta {
    private long numero;
    private String nombre;
    private String fecha;
    private int CVV;

    public Tarjeta(long numero, String nombre, String fecha, int CVV) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.CVV = CVV;
    }


    public boolean validarTarjeta() {
        try {
            verificarNumero(String.valueOf(this.numero));
            verificarNombre(this.nombre);
            verificarFecha(this.fecha);
            verificarCVV(String.valueOf(this.CVV));
            return true;
        } catch (IllegalArgumentException e) {
            // Captura las excepciones de validación y emite el mensaje de error correspondiente
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public static void verificarNumero(String numeroStr) {
        try {
            if (numeroStr == null || numeroStr.trim().length() != 16) {
                throw new IllegalArgumentException("El número de tarjeta debe tener exactamente 16 dígitos.");
            }
            Long.parseLong(numeroStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número de tarjeta contiene caracteres no válidos. Debe ser puramente numérico.");
        }
    }

     
    public static void verificarNombre(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del titular no puede estar vacío.");
            }
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El nombre del titular solo debe contener letras y espacios.");
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("El nombre del titular proporcionado es nulo.");
        }
    }


    public static void verificarFecha(String fecha) {
        try {
            if (fecha == null || !fecha.matches("^(0[1-9]|1[0-2])/(2[6-9]|[3-9][0-9])$")) {
                throw new IllegalArgumentException("La fecha de vencimiento debe cumplir el formato MM/AA (Ejemplo: 08/29).");
            }
            
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) e;
            }
            throw new IllegalArgumentException("Error al procesar la fecha de vencimiento.");
        }
    }


    public static void verificarCVV(String cvvStr) {
        try {
            if (cvvStr == null || cvvStr.trim().length() != 3) {
                throw new IllegalArgumentException("El código CVV debe tener exactamente 3 dígitos.");
            }
            Integer.parseInt(cvvStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El CVV contiene caracteres no válidos. Debe ser un número de 3 dígitos.");
        }
    }

    public long getNumero() { return numero; }
    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public int getCVV() { return CVV; }

    public String getNumeroEnmascarado() {
        String n = String.valueOf(numero);
        String ultimos = n.length() >= 4 ? n.substring(n.length() - 4) : n;
        return "**** **** **** " + ultimos;
    }
}