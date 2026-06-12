/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import com.mycompany.proyectoventa_de_entradas.ProyectoVenta_de_Entradas;
import modelo.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VistaRegistro extends JPanel {
    private JTextField txtNombres, txtApellidos, txtDni;
    private JPasswordField txtClave;
    private ProyectoVenta_de_Entradas nav;

    public VistaRegistro(ProyectoVenta_de_Entradas nav) {
        this.nav = nav;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Registro de Cliente");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Nuevos campos de texto actualizados
        txtNombres = new JTextField(15);
        txtApellidos = new JTextField(15);
        txtDni = new JTextField(15);
        txtClave = new JPasswordField(15);

        JButton btnRegistrar = new JButton("Registrarme");
        JButton btnVolver = new JButton("Volver al Login");

        btnRegistrar.addActionListener((ActionEvent e) -> {
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String dni = txtDni.getText();
            String clave = new String(txtClave.getPassword());

            // Validación simple
            if (nombres.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || clave.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Llene todos los campos");
                return;
            }

            // Aquí creamos el cliente con los 5 parámetros requeridos
            // (0 puntos iniciales, nombres, apellidos, dni, clave)
            Cliente nuevo = new Cliente(0, nombres, apellidos, dni, clave);
            
            if (Cliente.registrarCliente(nuevo)) {
                JOptionPane.showMessageDialog(this, "Registro exitoso. Ya puede iniciar sesión.");
                nav.cambiarVista("Login");
            } else {
                JOptionPane.showMessageDialog(this, "El DNI ya se encuentra registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> nav.cambiarVista("Login"));

        // Diseño en la cuadrícula ajustado para los nuevos campos
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; add(lblTitulo, gbc);
        gbc.gridwidth = 1;
        
        gbc.gridy = 1; add(new JLabel("Nombres:"), gbc); 
        gbc.gridx = 1; add(txtNombres, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Apellidos:"), gbc); 
        gbc.gridx = 1; add(txtApellidos, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("DNI (Usuario):"), gbc); 
        gbc.gridx = 1; add(txtDni, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Contraseña:"), gbc); 
        gbc.gridx = 1; add(txtClave, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; add(btnRegistrar, gbc);
        gbc.gridy = 6; add(btnVolver, gbc);
    }
}