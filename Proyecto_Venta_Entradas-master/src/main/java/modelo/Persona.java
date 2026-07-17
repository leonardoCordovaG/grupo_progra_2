/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author juane
 */
public abstract class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombres;
    private String apellidos;
    private String dni;
    private String contrasena;

    public Persona(String nombres, String apellidos, String dni, String contrasena) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.contrasena = contrasena;
    }
    
    public boolean  registarTarjeta(){
        return false;
    }
    public boolean eliminarTarjeta(){
        return false;
    }
    public boolean anularVenta(){
        return false;
    }
    public boolean comprar(){
        return false;
    }
    public String getDni() {
        return dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombres() {
        return nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
}
