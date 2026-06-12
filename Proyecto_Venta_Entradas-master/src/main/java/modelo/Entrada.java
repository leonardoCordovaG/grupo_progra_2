package modelo;

public class Entrada {
    private int numero;
    private String estado;

    public Entrada(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public boolean vender() {
        return false;
    }

    public boolean liberar() {
        return false;
    }

    public int getNumero() { return numero; }
    public String getEstado() { return estado; }
}
