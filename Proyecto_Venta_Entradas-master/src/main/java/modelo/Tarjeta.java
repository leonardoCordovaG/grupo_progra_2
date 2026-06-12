package modelo;

public class Tarjeta {
    // long en lugar de int: los números de tarjeta tienen 16 dígitos y desbordan int
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
