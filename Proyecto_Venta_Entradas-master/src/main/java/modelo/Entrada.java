package modelo;

public class Entrada {
    private int numero;
    private String estado;

    public Entrada(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public boolean vender() {
        if ("disponible".equals(estado)) {
            estado = "vendida";
            return true;
        }
        return false;
    }

    public boolean liberar() {
        if ("vendida".equals(estado)) {
            estado = "disponible";
            return true;
        }
        return false;
    }

    public int getNumero() { return numero; }
    public String getEstado() { return estado; }
}
