package modelo;

import java.io.Serializable;

public class Entrada implements Serializable {
    private static final long serialVersionUID = 1L;

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
