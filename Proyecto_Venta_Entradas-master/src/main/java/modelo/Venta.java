package modelo;

import java.util.Date;

public class Venta {
    private Date fecha;
    private int monto;
    private Zona zonaAsociada;
    private Entrada entradaAsociada;

    public Venta(Date fecha, int monto) {
        this.fecha = fecha;
        this.monto = monto;
    }

    // Constructor extendido para integrarse con ControladorTicket
    public Venta(Date fecha, int monto, Zona zona, Entrada entrada) {
        this.fecha = fecha;
        this.monto = monto;
        this.zonaAsociada = zona;
        this.entradaAsociada = entrada;
    }

    public boolean anular() {
        return false;
    }

    public int getMonto() { return monto; }
    public Date getFecha() { return fecha; }
    public Zona getZonaAsociada() { return zonaAsociada; }
    public Entrada getEntradaAsociada() { return entradaAsociada; }
}
