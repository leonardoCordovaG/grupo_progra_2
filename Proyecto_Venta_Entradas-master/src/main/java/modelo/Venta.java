/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author juane
 */
public class Venta {
    private Date fecha;
    private int monto;

    public Venta(Date fecha, int monto) {
        this.fecha = fecha;
        this.monto = monto;
    }
    
    public boolean anular(){
        return false;
    }
    
}
