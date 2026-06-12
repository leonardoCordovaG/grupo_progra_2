package modelo;

import java.util.Date;

public class Venta {
    private Date fecha;
    private int monto;
    private Zona zonaAsociada;
    private Entrada entradaAsociada;
    private String conciertoNombre;
    private int cantidadEntradas;

    public Venta(Date fecha, int monto) {
        this.fecha = fecha;
        this.monto = monto;
    }

    // Constructor extendido para ControladorTicket e historial
    public Venta(Date fecha, int monto, Zona zona, Entrada entrada) {
        this.fecha = fecha;
        this.monto = monto;
        this.zonaAsociada = zona;
        this.entradaAsociada = entrada;
    }

    // Constructor completo con nombre del concierto y cantidad para el historial
    public Venta(Date fecha, int monto, Zona zona, Entrada entrada, String conciertoNombre, int cantidadEntradas) {
        this.fecha = fecha;
        this.monto = monto;
        this.zonaAsociada = zona;
        this.entradaAsociada = entrada;
        this.conciertoNombre = conciertoNombre;
        this.cantidadEntradas = cantidadEntradas;
    }

    public boolean anular() {
        return true;
    }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getMonto() { return monto; }
    public void setMonto(int monto) { this.monto = monto; }

    public Zona getZonaAsociada() { return zonaAsociada; }
    public Entrada getEntradaAsociada() { return entradaAsociada; }
    public String getConciertoNombre() { return conciertoNombre != null ? conciertoNombre : "-"; }
    public int getCantidadEntradas() { return cantidadEntradas; }
}
