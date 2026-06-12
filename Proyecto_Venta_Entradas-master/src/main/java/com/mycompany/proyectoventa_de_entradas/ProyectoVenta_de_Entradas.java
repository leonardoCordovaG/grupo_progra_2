
package com.mycompany.proyectoventa_de_entradas;

import controlador.ControladorAdminDashboard;
import java.util.ArrayList;
import java.util.Date;
import modelo.Concierto;
import modelo.Venta;
import vista.frmAdminDashboard;

/**
 *
 * @author juane
 */
public class ProyectoVenta_de_Entradas {

    public static void main(String[] args) {
       
        ArrayList<Concierto> conciertosIniciales = new ArrayList<>();
        ArrayList<Venta> ventasIniciales = new ArrayList<>();

      
        ventasIniciales.add(new Venta(new Date(), 150));
        ventasIniciales.add(new Venta(new Date(System.currentTimeMillis() - 86400000), 320));
        ventasIniciales.add(new Venta(new Date(System.currentTimeMillis() - 172800000), 90));

   
        frmAdminDashboard vistaDashboard = new frmAdminDashboard();
        
  
        ControladorAdminDashboard controlador = new ControladorAdminDashboard(vistaDashboard, conciertosIniciales, ventasIniciales);
        
    
        controlador.iniciar();
    }
}
