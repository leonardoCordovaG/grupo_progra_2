/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juane
 */
public class Zona {
    
    private String nombre;
    private int capacidad;
    private int precio;
    private Entrada[] lista_entradas;


    public Zona(String nombre, int capacidad, int precio, Entrada[] listaEntradas) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.lista_entradas = listaEntradas;
    }
    
    private boolean generarEntradas(){
        return false;
    }
    
    public Entrada[] mostrarEntrada(){
        return lista_entradas;
    }
    
    public Entrada[] venderEntrada(int numero){
        return lista_entradas;
    }
    
}
