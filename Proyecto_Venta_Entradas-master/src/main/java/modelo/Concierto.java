/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author juane
 */
public class Concierto {
        private String nombre;
        private Date fecha;
        private ArrayList<Zona> listaZonas = new ArrayList<>();

    public Concierto(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }
     
    public boolean agregarZona(Zona nuevaZona){
        if(nuevaZona != null) {
            listaZonas.add(nuevaZona);
            return true;
        }
        return false;
    }
    
   
    public boolean agregarZona(String nombre){
        return agregarZona(new Zona(nombre, 0, 0, new Entrada[0]));
    }
    
    
    
    public boolean eliminarZona(String nombreZona){
        return listaZonas.removeIf(z -> z.getNombre().equalsIgnoreCase(nombreZona));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Zona> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(ArrayList<Zona> listaZonas) {
        this.listaZonas = listaZonas;
    }
    
        
    
}
