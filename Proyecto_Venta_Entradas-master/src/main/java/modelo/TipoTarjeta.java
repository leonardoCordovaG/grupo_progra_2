/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Keple
 */
public class TipoTarjeta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int longitudNumero;
    private int longitudCVV;
    private double porcentajeDescuento;

    public TipoTarjeta(String nombre, int longitudNumero, int longitudCVV, double porcentajeDescuento) {
        this.nombre = nombre;
        this.longitudNumero = longitudNumero;
        this.longitudCVV = longitudCVV;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public boolean validarNumero(long numero) {
        return String.valueOf(numero).length() == longitudNumero;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getLongitudNumero() { return longitudNumero; }
    public void setLongitudNumero(int longitudNumero) { this.longitudNumero = longitudNumero; }

    public int getLongitudCVV() { return longitudCVV; }
    public void setLongitudCVV(int longitudCVV) { this.longitudCVV = longitudCVV; }

    public double getPorcentajeDescuento() { return porcentajeDescuento; }
    public void setPorcentajeDescuento(double porcentajeDescuento) { this.porcentajeDescuento = porcentajeDescuento; }

    @Override
    public String toString() { return nombre; }
}
