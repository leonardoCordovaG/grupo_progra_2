/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juane
 */
public class Usuario extends Persona {

    private boolean estado;

    public Usuario(boolean estado, String nombres, String apellidos, String dni, String contrasena) {
        super(nombres, apellidos, dni, contrasena);
        this.estado = estado;
    }
   public void registrarZonas(){
       
   }
    
    
    
    
}
