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

public class VistaLogin extends JPanel {
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private ProyectoVenta_de_Entradas nav; // Referencia al Main para poder navegar

    public VistaLogin(ProyectoVenta_de_Entradas nav) {
        this.nav = nav;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        
        txtUsuario = new JTextField(15);
        txtClave = new JPasswordField(15);
        
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnIrRegistro = new JButton("Crear nueva cuenta");

        btnIngresar.addActionListener((ActionEvent e) -> {
            String usuario = txtUsuario.getText();
            String clave = new String(txtClave.getPassword());

            if (usuario.equals("admin") && clave.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador");
                // nav.cambiarVista("DashboardAdmin"); // Integrante 5
            } else {
                Cliente c = Cliente.ingresar(usuario, clave);
                if (c != null) {
                    JOptionPane.showMessageDialog(this, "Bienvenido " + c.getNombres());
                    // nav.cambiarVista("Cartelera"); // Integrante 2
                } else {
                    JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnIrRegistro.addActionListener(e -> nav.cambiarVista("Registro"));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; add(lblTitulo, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1; add(new JLabel("Usuario:"), gbc); gbc.gridx = 1; add(txtUsuario, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Contraseña:"), gbc); gbc.gridx = 1; add(txtClave, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; add(btnIngresar, gbc);
        gbc.gridy = 4; add(btnIrRegistro, gbc);
    }
}