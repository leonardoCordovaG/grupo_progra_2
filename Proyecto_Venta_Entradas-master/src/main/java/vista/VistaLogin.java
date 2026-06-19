package vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {

    public JTextField    txtUsuario;
    public JPasswordField txtClave;
    public JButton       btnIngresar;
    public JButton       btnIrRegistro;

    public VistaLogin() {
        setTitle("Sistema de Venta de Entradas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        txtUsuario    = new JTextField(15);
        txtClave      = new JPasswordField(15);
        btnIngresar   = new JButton("Ingresar");
        btnIrRegistro = new JButton("Crear nueva cuenta");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; add(lblTitulo, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1;                add(txtUsuario, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;                add(txtClave, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; add(btnIngresar, gbc);
        gbc.gridy = 4;                                    add(btnIrRegistro, gbc);

        pack();
    }
}
