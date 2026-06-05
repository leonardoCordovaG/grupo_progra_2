/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juane
 */
public class Cliente extends Persona {
     
    private int puntos;
    
    // "Base de datos" simulada en memoria para que funcione tu login
    private static List<Cliente> clientesRegistrados = new ArrayList<>();

    public Cliente(int puntos, String nombres, String apellidos, String dni, String contrasena) {
        super(nombres, apellidos, dni, contrasena);
        this.puntos = puntos;
    }
    
    // 1. Modificamos 'void' por 'Cliente' y lo hacemos 'static'. 
    // Así puedes llamar a Cliente.ingresar(...) sin tener que crear un cliente vacío primero.
    public static Cliente ingresar(String usuarioDni, String clave){
        for (Cliente c : clientesRegistrados) {
            // Nota: Asegúrate de que la clase padre (Persona) tenga creados 
            // los métodos getDni() y getContrasena() para que esto funcione.
            if (c.getDni().equals(usuarioDni) && c.getContrasena().equals(clave)) {
                return c; // Credenciales correctas
            }
        }
        return null; // Credenciales incorrectas
    }
    
    // 2. Añadimos el método de registro que necesita tu pantalla VistaRegistro
    public static boolean registrarCliente(Cliente nuevoCliente) {
        for (Cliente c : clientesRegistrados) {
            if (c.getDni().equals(nuevoCliente.getDni())) {
                return false; // Evita registrar dos veces el mismo DNI
            }
        }
        clientesRegistrados.add(nuevoCliente);
        return true;
    }

    // Getters y Setters de la clase Cliente
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}